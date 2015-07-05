package com.dasinong.ploughHelper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasinong.ploughHelper.model.User;

//Add some short cut here to check/sync frontend and backend status
@Controller
public class ShortCutController {
	private static final Logger logger = LoggerFactory.getLogger(ShortCutController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/checkIsLogin", produces="application/json")
	@ResponseBody
	public Object checkLogin(HttpServletRequest request, HttpServletResponse response){
		HashMap<String,Object> result = new HashMap<String,Object>();
		User user = (User) request.getSession().getAttribute("User");
		if (user==null){
			result.put("respCode", 100);
			result.put("message","尚未登陆");
			return result;
		}
		else{
			result.put("respCode", 200);
			result.put("message","用户已登陆");
			return result;
		}
		
	}

}
