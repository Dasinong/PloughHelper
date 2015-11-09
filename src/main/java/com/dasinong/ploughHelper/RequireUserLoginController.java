package com.dasinong.ploughHelper;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import com.dasinong.ploughHelper.exceptions.UserIsNotLoggedInException;
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
