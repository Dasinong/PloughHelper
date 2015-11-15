package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IInstitutionDao;
import com.dasinong.ploughHelper.dao.IInstitutionEmployeeApplicationDao;
import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.dao.IWeatherSubscriptionDao;
import com.dasinong.ploughHelper.exceptions.InvalidParameterException;
import com.dasinong.ploughHelper.exceptions.RequireUserTypeException;
import com.dasinong.ploughHelper.exceptions.UserTypeAlreadyDefinedException;
import com.dasinong.ploughHelper.facade.IInstitutionEmployeeApplicationFacade;
import com.dasinong.ploughHelper.model.Institution;
import com.dasinong.ploughHelper.model.InstitutionEmployeeApplication;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.UserType;
import com.dasinong.ploughHelper.model.WeatherSubscription;
import com.dasinong.ploughHelper.util.HttpServletRequestX;

@Controller
public class InstitutionEmployeeApplicationController extends RequireUserLoginController {

	@RequestMapping(value = "/institutionEmployeeApplications", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object createInstitutionEmployeeApplication(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HttpServletRequestX requestX = new HttpServletRequestX(request);
		IInstitutionEmployeeApplicationFacade facade = (IInstitutionEmployeeApplicationFacade)
				ContextLoader.getCurrentWebApplicationContext().getBean("institutionEmployeeApplicationFacade");
		
		User user = this.getLoginUser(request);
		String cellphone = requestX.getString("cellphone");
		String code = requestX.getString("code");
		String title = requestX.getString("title");
		String region = requestX.getString("region");
		
		return facade.create(user, cellphone, code, title, region);
	}
}
