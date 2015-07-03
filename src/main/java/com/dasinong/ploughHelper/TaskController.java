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

import com.dasinong.ploughHelper.facade.ITaskFacade;
import com.dasinong.ploughHelper.model.User;


@Controller
public class TaskController {
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
		
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
		Long fId;
		try{
			fId = Long.parseLong(request.getParameter("fieldId"));
		}catch(Exception e){
			result.put("respCode",300);
			result.put("message", "fieldId参数或内容错误");
			return result;
		}
		
		ITaskFacade tf = (ITaskFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskFacade");
		return tf.getAllTask(fId);
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
		Long fId;
		Long currentStageId;
		try{
			fId = Long.parseLong(request.getParameter("fieldId"));
			currentStageId = Long.parseLong(request.getParameter("currentStageId"));
		}catch(Exception e){
			result.put("respCode",300);
			result.put("message", "fieldId,currentStageId参数或内容错误");
			return result;
		}
		
		ITaskFacade tf = (ITaskFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskFacade");
		return tf.getCurrentTask(fId, currentStageId);
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
		
		Long fieldId;
		try{
			fieldId = Long.parseLong(request.getParameter("fieldId"));
		}catch(Exception e){
			result.put("respCode",302);
			result.put("message", "fieldId参数或内容错误");
			return result;
		}
		
		
		String taskIds = request.getParameter("taskIds");
		String taskStatuss = request.getParameter("taskStatuss");
		String taskId =  request.getParameter("taskId");
		String taskStatus =  request.getParameter("taskStatus");
		
		try{
			if (taskIds==null && taskStatuss==null &&taskId!=null && taskStatus!=null){
				Long id = Long.parseLong(taskId);
				boolean status = Boolean.parseBoolean(taskStatus);
				ITaskFacade tf = (ITaskFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskFacade");
				return tf.updateTask(fieldId, id, status);
			}
			else if (taskIds!=null && taskStatuss!=null &&taskId==null && taskStatus==null){
			    String[] unitIds = taskIds.split(",");
			    String[] unitStatuss = taskStatuss.split(",");
			    if (unitIds.length!=unitStatuss.length){
			    	result.put("respCode", 301);
					result.put("message", "输入任务表长度不匹配" );
					return result;
			    }
			    HashMap<Long,Boolean> tasks = new HashMap<Long,Boolean>();
			    for (int i=0;i<unitIds.length;i++){
			    	tasks.put(Long.parseLong(unitIds[i]), Boolean.parseBoolean(unitStatuss[i]));
			    }
				ITaskFacade tf = (ITaskFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskFacade");
				return tf.updateTasks(fieldId, tasks);
			}
			else{
				result.put("respCode", 300);
				result.put("message", "输入参数错误" );
				return result;
			}
		}catch(Exception e){
			result.put("respCode", 500);
			result.put("message", e.getMessage() );
			return result;
		}
	}
}
