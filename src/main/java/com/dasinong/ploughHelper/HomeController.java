package com.dasinong.ploughHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
import com.dasinong.ploughHelper.dao.TaskDao;
import com.dasinong.ploughHelper.dao.UserDao;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;


@Controller
public class HomeController {
	
private static final Logger logger = LoggerFactory.getLogger(Test1Controller.class);
	
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
		if (user.getFields()==null){
			result.put("respCode",110);
			result.put("message","用户没有田地，请先添加");
			return result;
		}
		
		FieldDao fieldDao = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		
		String fieldId =  request.getParameter("fieldId");
		List<Long> fieldIds = new ArrayList<Long>();
		for (Field f: user.getFields()){
			fieldIds.add(f.getFieldId());
		}
		result.put("fieldIds",fieldIds);
		
		if (fieldId==null || fieldId.equals("")){
			result.put("respCode", 200);
			result.put("Message", "读取田地成功");
			Field f = user.getFields().iterator().next();
			FieldWrapper fw = new FieldWrapper(f);
			result.put("currentField",fw);
			return result;
		}
		else{
			Long fid = Long.parseLong(fieldId);
			Field f= fieldDao.findById(fid);
			if (f==null){
				result.put("respCode",120);
				result.put("message","fieldId不存在");
				return result;
			}else{
				result.put("respCode", 200);
				result.put("Message", "读取田地成功");
				FieldWrapper fw = new FieldWrapper(f);
				result.put("currentField",fw);
				return result;
			}
		}
	}
	
	
	@RequestMapping(value = "/updateTask", produces="application/json")
	@ResponseBody
	public Object updateTask(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		
		TaskDao taskDao = (TaskDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskDao");
		
		String taskId =  request.getParameter("taskId");
		String status =  request.getParameter("taskStatus");
		
		//TODO:
		
	    return result;
	}
	
	
	

}
