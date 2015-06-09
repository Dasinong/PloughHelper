package com.dasinong.ploughHelper;

import java.io.FileNotFoundException;
import java.io.IOException;
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

import com.dasinong.ploughHelper.contentLoader.LoadLocation;
import com.dasinong.ploughHelper.dao.FieldDao;
import com.dasinong.ploughHelper.inputParser.FieldParser;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Task;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;
import com.dasinong.ploughHelper.outputWrapper.TaskWrapper;


@Controller
public class TaskController {
	private static final Logger logger = LoggerFactory.getLogger(Test1Controller.class);
	
	@RequestMapping(value = "/getAllTask", produces="application/json")
	@ResponseBody
	public Object getAllTask(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		String fieldId = request.getParameter("fieldId");
		if (fieldId==null){
			result.put("respCode",300);
			result.put("message", "缺少field参数");
			return result;
		}
		Long fId = Long.parseLong(fieldId);
		
		
	    try {
			FieldDao fieldDao = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao"); 
			Field fd = fieldDao.findById(fId);
			
			if (fd==null){
				result.put("respCode", 301);
				result.put("message", "找不到田地");
				return result;
			}
		    
		    
			List<TaskWrapper> data = new ArrayList<TaskWrapper>();
			for (Task t : fd.getTasks().values()){
				TaskWrapper tw = new TaskWrapper(t);
				data.add(tw);
			}
			result.put("respCode", 200);
			result.put("message", "获取任务成功");
			result.put("data", data);
			return result;
		} catch (Exception e) {
			result.put("respCode",500);
			result.put("message", e.getCause());
			return result;
		}
	}
	
	@RequestMapping(value = "/getCurrentTask", produces="application/json")
	@ResponseBody
	public Object getCurrentTask(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		String fieldId = request.getParameter("fieldId");
		String currentStageId  = request.getParameter("currentStageId");
		if (fieldId==null){
			result.put("respCode",300);
			result.put("message", "缺少参数");
			return result;
		}
		Long fId = Long.parseLong(fieldId);
		
		
	    try {
			FieldDao fieldDao = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao"); 
			Field fd = fieldDao.findById(fId);
			
			
			if (fd==null){
				result.put("respCode", 301);
				result.put("message", "找不到田地");
				return result;
			}
		    
			List<TaskWrapper> data = new ArrayList<TaskWrapper>();
			for (Task t : fd.getTasks().values()){
				TaskWrapper tw = new TaskWrapper(t);
				data.add(tw);
			}
			result.put("respCode", 200);
			result.put("message", "获取任务成功");
			result.put("data", data);
			return result;
		} catch (Exception e) {
			result.put("respCode",500);
			result.put("message", e.getCause());
			return result;
		}
	}
}
