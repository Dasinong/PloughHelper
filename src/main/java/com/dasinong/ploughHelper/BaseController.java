package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.exceptions.GenerateAppAccessTokenException;
import com.dasinong.ploughHelper.exceptions.GenerateUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.InvalidAppAccessTokenException;
import com.dasinong.ploughHelper.exceptions.InvalidUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.MultipleUserAccessTokenException;
import com.dasinong.ploughHelper.exceptions.InvalidParameterException;
import com.dasinong.ploughHelper.exceptions.MissingParameterException;
import com.dasinong.ploughHelper.exceptions.ResourceNotFoundException;
import com.dasinong.ploughHelper.exceptions.UserIsNotLoggedInException;
import com.dasinong.ploughHelper.exceptions.UserNotFoundInSessionException;
import com.dasinong.ploughHelper.exceptions.ViewerContextNotInitializedException;
import com.dasinong.ploughHelper.exceptions.WeatherAlreadySubscribedException;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.viewerContext.ViewerContext;

public class BaseController {

	// TODO (xiahonggao): make all controllers extend this controller
	
	public ViewerContext getViewerContext(HttpServletRequest request) throws Exception {
		ViewerContext vc = (ViewerContext) request.getAttribute(ViewerContext.REQUEST_KEY);
		if (vc == null) {
			throw new ViewerContextNotInitializedException();
		}	
		return vc;
	}

	public User getLoginUser(HttpServletRequest request) throws Exception {
		ViewerContext vc = this.getViewerContext(request);
		if (!vc.isUserLogin()) {
			return null;
		}
		
		IUserDao userDao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		return userDao.findById(vc.getUserId());
	}
	
	/**
	 * Range 100 - 200 is reserved for session/token
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(UserNotFoundInSessionException.class)
	@ResponseBody
	public Object handleUserNotFoundError(
		HttpServletRequest req, 
		UserNotFoundInSessionException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 100);
		result.put("message", "用户未找到");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(UserIsNotLoggedInException.class)
	@ResponseBody
	public Object handleUserIsNotLoggedInException(
		HttpServletRequest req, 
		UserIsNotLoggedInException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 100);
		result.put("message", "用户没有登录");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(InvalidAppAccessTokenException.class)
	@ResponseBody
	public Object handleInvalidAppAccessTokenException(
		HttpServletRequest req, 
		InvalidAppAccessTokenException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 110);
		result.put("message", "不合法的App Access Token");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(GenerateAppAccessTokenException.class)
	@ResponseBody
	public Object handleGenerateAppAccessTokenException(
		HttpServletRequest req, 
		GenerateAppAccessTokenException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 111);
		result.put("message", "无法产生App Access Token (appId=" + exception.getAppId() + ")");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(InvalidUserAccessTokenException.class)
	@ResponseBody
	public Object handleInvalidUserAccessTokenException(
		HttpServletRequest req, 
		InvalidUserAccessTokenException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> errorData = new HashMap<String,Object>();
		errorData.put("accessToken", exception.getToken());
		result.put("respCode", 120);
		result.put("message", "不合法的User Access Token");
		result.put("data", errorData);
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(MultipleUserAccessTokenException.class)
	@ResponseBody
	public Object handleMultipleUserAccessTokenException(
		HttpServletRequest req, 
		MultipleUserAccessTokenException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 121);
		result.put("message", "多个User Access Token");
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(GenerateUserAccessTokenException.class)
	@ResponseBody
	public Object handleGenerateUserAccessTokenException(
		HttpServletRequest req, 
		GenerateUserAccessTokenException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 122);
		result.put("message", "无法生成UserAccessToken");
		return result;
	}
	
	/**
	 * Range 300 - 400 is reserved for parameter validation
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(MissingParameterException.class)
	@ResponseBody
	public Object hanleMissingParameterError(
		HttpServletRequest req, 
		MissingParameterException exception
	) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> errorData = new HashMap<String, Object>();
		errorData.put("name", exception.getParamName());
		result.put("respCode", 300);
		result.put("message", "参数缺失");
		result.put("data", errorData);
		return result;
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(InvalidParameterException.class)
	@ResponseBody
	public Object hanleInvalidParameterException(
		HttpServletRequest req, 
		InvalidParameterException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String, String> errorData = exception.getParams();
		result.put("respCode", 301);
		result.put("message", "参数不正确");
		result.put("data", errorData);
		return result;
	}
	
	/**
	 * range 400 - 499 is reserved for resource errors
	 * 500 is reversed for unknown server error
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseBody
	public Object handleResourceNotFound(
		HttpServletRequest req, 
		ResourceNotFoundException exception
	) {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> errorData = new HashMap<String,Object>();
		errorData.put("resourceType", exception.getResourceType());
		errorData.put("resourceId", exception.getResourceId());
		result.put("respCode", 404);
		result.put("message", "请求的资源不存在");
		result.put("data", errorData);
		return result;
	}
	
	/**
	 * Range 1000 - 1100 is reserved for database exception
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(DataAccessException.class)
	@ResponseBody
	public Object handleDatabaseError(
		HttpServletRequest req, 
		DataAccessException exception
	) {
		exception.printStackTrace();
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> errorData = new HashMap<String,Object>();
		errorData.put("class", exception.getClass());
		errorData.put("stacktrace", exception.getStackTrace());
		result.put("respCode", 1000);
		result.put("message", "数据库访问错误");
		result.put("data", errorData);
		return result;
	}
		
	/**
	 * Range 1100 - 1200 is reserved for weather subscription
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(WeatherAlreadySubscribedException.class)
	@ResponseBody
	public Object handleWeatherAlreadySubscribed(HttpServletRequest req, Exception exception) {
		Map<String,Object> result = new HashMap<String,Object>();
		result.put("respCode", 1100);
		result.put("message", "已经关注了该地区得天气");
		return result;
	}
	
	/**
	 * This catches every exception and returns 500 which means
	 * uncaught internal error.
	 */
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Object handleError(HttpServletRequest req, Exception exception) {
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> errorData = new HashMap<String,Object>();
		errorData.put("class", exception.getClass());
		errorData.put("stacktrace", exception.getStackTrace());
		result.put("respCode", 500);
		result.put("message", "服务器内部错误");
		result.put("data", errorData);
		return result;
	}
	
}
