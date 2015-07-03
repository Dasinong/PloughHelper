package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.facade.IHomeFacade;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Task;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.modelTran.LaoNong;
import com.dasinong.ploughHelper.modelTran.Duanzi;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
   
	@RequestMapping(value = "/home", produces="application/json")
	@ResponseBody
	public Object home(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		String fieldId =  request.getParameter("fieldId");
		IHomeFacade hf = (IHomeFacade) ContextLoader.getCurrentWebApplicationContext().getBean("homeFacade");
		return hf.LoadHome(user, fieldId);
	}
	
	
	
	
	@RequestMapping(value = "/getLaoNong", produces="application/json")
	@ResponseBody
	public Object getLaoNong(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		
		Duanzi duanzi = LaoNong.getDuanzi();
		
		result.put("respcode", 200);
		result.put("message", "获取段子成功");
		result.put("data",duanzi);
		return result;
	}

}
