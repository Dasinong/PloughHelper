package com.dasinong.ploughHelper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.facade.ITaskSpecFacade;
import com.dasinong.ploughHelper.model.User;

@Controller
public class TaskSpecController {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskSpecController.class);
	
	ITaskSpecFacade tsf;
	
	@RequestMapping(value = "/getTaskSpec", produces="application/json")
	@ResponseBody
	public Object getTaskSpec(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		HashMap<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		tsf =  (ITaskSpecFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecFacade");
		Long taskSpecId;
		try{
			taskSpecId = Long.parseLong(request.getParameter("taskSpecId"));
		}
		catch(Exception e){
			result.put("respCode",300);
			result.put("message","参数格式或内容错误");
			return result;
		}

		return tsf.getTaskSpec(taskSpecId);

	}
	
	@RequestMapping(value = "/getSteps", produces="application/json")
	@ResponseBody
	public Object getSteps(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		HashMap<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		tsf =  (ITaskSpecFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecFacade");
		Long taskSpecId;
		Long fieldId;
		try{
			taskSpecId = Long.parseLong(request.getParameter("taskSpecId"));
			fieldId = Long.parseLong(request.getParameter("fieldId"));
		}
		catch(Exception e){
			result.put("respCode",301);
			result.put("message","taskSpecId或fieldId参数格式或内容错误");
			return result;
		}

		return tsf.getSteps(taskSpecId,fieldId);
	}
}
