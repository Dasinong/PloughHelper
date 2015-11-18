package com.dasinong.ploughHelper.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.dasinong.ploughHelper.accessTokenManager.AppAccessTokenManager;
import com.dasinong.ploughHelper.accessTokenManager.UserAccessTokenManager;
import com.dasinong.ploughHelper.dao.IUserAccessTokenDao;
import com.dasinong.ploughHelper.exceptions.InvalidAppAccessTokenException;
import com.dasinong.ploughHelper.exceptions.InvalidUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.UserAccessTokenExpiredException;
import com.dasinong.ploughHelper.model.AppAccessToken;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.UserAccessToken;
import com.dasinong.ploughHelper.viewerContext.ViewerContext;

/**
 * 
 * @author xiahonggao
 *
 */
public class ViewerContextInitializer extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		ViewerContext viewerContext = new ViewerContext();

		// Set deviceId if any
		String deviceId = request.getParameter("deviceId");
		if (deviceId != null) {
			viewerContext.setDeviceId(deviceId);
		}

		// Set appId if any
		// appId is passed if there is no login user
		// appId should not be passed if app/user access token is passed
		String appId = request.getParameter("appId");
		if (appId != null) {
			viewerContext.setAppId(Long.valueOf(appId));
		}

		String token = request.getParameter("accessToken");

		// initialize viewer context from session
		if (token == null || "".equals(token)) {
			// TODO (xiahonggao): deprecate session
			// response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			// return false;

			User user = (User) request.getSession().getAttribute("User");
			if (user != null) {
				viewerContext.setUserId(user.getUserId());
			}

			request.setAttribute(ViewerContext.REQUEST_KEY, viewerContext);
			return true;
		}

		// initialize viewer context from app access token
		if (this.isAppAccessTokenFormat(token)) {
			AppAccessTokenManager manager = new AppAccessTokenManager();
			try {
				AppAccessToken accessToken = manager.parse(token);
				viewerContext.setAppId(accessToken.getAppId());
			} catch (InvalidAppAccessTokenException ex) {
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);
				return false;
			}

			request.setAttribute(ViewerContext.REQUEST_KEY, viewerContext);
			return true;
		}

		// initialize viewer context from user access token
		try {
			UserAccessTokenManager manager = new UserAccessTokenManager();
			UserAccessToken accessToken = manager.parse(token);
			viewerContext.setAppId(accessToken.getAppId());
			viewerContext.setUserId(accessToken.getUserId());

			manager.renew(accessToken);
		} catch (InvalidUserAccessTokenException ex) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			return false;
		} catch (UserAccessTokenExpiredException ex) {
			// client side is supposed to show login page once 401 is seen
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}

		request.setAttribute(ViewerContext.REQUEST_KEY, viewerContext);
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (request.getAttribute(ViewerContext.REQUEST_KEY) != null) {
			request.removeAttribute(ViewerContext.REQUEST_KEY);
		}
	}

	private boolean isAppAccessTokenFormat(String token) {
		String[] parts = token.split("\\|");
		return parts != null && parts.length > 1;
	}
}
