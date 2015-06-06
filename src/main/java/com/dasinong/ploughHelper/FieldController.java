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

import com.dasinong.ploughHelper.dao.FieldDao;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.User;



@Controller
public class FieldController {
	private static final Logger logger = LoggerFactory.getLogger(Test1Controller.class);
	
	@RequestMapping(value = "/createField", produces="application/json")
	@ResponseBody
	public Object createField(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
	    FieldDao fd = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
	   
	    Field field = new Field();
		
		
		return result;
	}
		
}
