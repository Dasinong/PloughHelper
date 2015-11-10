package com.dasinong.ploughHelper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.exceptions.UserAccessTokenNotFoundException;
import com.dasinong.ploughHelper.exceptions.UserIsNotLoggedInException;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.viewerContext.ViewerContext;

public class RequireUserLoginController extends BaseController {

	@ModelAttribute
	public void assertUserIsLoggedIn(HttpServletRequest request) throws Exception {
		ViewerContext vc = this.getViewerContext(request);
		if (!vc.isUserLogin()) {
			throw new UserIsNotLoggedInException();
		}
	}
	
}