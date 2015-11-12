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

import com.dasinong.ploughHelper.exceptions.InvalidParameterException;
import com.dasinong.ploughHelper.exceptions.MissingParameterException;
import com.dasinong.ploughHelper.facade.ITaskFacade;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.util.HttpServletRequestX;


@Controller
public class TaskController extends RequireUserLoginController {
	private static final Logger logger = LoggerFactory.getLogger(TaskController.class);
		
	@RequestMapping(value = "/getAllTask", produces="application/json")
	@ResponseBody
	public Object getAllTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		
		Long fId;
		try{
			fId = Long.parseLong(request.getParameter("fieldId"));
		}catch(Exception e){
			throw new InvalidParameterException("fieldId","long");
		}
		
		ITaskFacade tf = (ITaskFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskFacade");
		return tf.getAllTask(fId);
	}
	
	@RequestMapping(value = "/getCurrentTask", produces="application/json")
	@ResponseBody
	public Object getCurrentTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		
		Long fId;
		Long currentStageId;
		try{
			fId = Long.parseLong(request.getParameter("fieldId"));
			currentStageId = Long.parseLong(request.getParameter("currentStageId"));
		}catch(Exception e){
			throw new InvalidParameterException("fieldId,currentStageId","long,long");
		}
		
		ITaskFacade tf = (ITaskFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskFacade");
		return tf.getCurrentTask(fId, currentStageId);
	}
	
	@RequestMapping(value = "/updateTask", produces="application/json")
	@ResponseBody
	public Object updateTask(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		HttpServletRequestX requestX = new HttpServletRequestX(request);
		Long fieldId = requestX.getLong("fieldId");

		
		if (requestX.hasParameter("taskId") && requestX.hasParameter("taskStatus")){
			Long id = requestX.getLong("taskId");
			boolean status = requestX.getBool("taskStatus");
			ITaskFacade tf = (ITaskFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskFacade");
			return tf.updateTask(fieldId, id, status);
		}
		else if (requestX.hasParameter("taskIds") && requestX.hasParameter("taskStatuss")){
			String taskIds = requestX.getString("taskIds");
			String taskStatuss = requestX.getString("taskStatuss");
			String[] unitIds = taskIds.split(",");
		    String[] unitStatuss = taskStatuss.split(",");	
		    if (unitIds.length!=unitStatuss.length){
		    	throw new InvalidParameterException("unitIds;unitStatuss","2,3,4;true,true,false");
		    }
		    HashMap<Long,Boolean> tasks = new HashMap<Long,Boolean>();
		    for (int i=0;i<unitIds.length;i++){
		    	tasks.put(Long.parseLong(unitIds[i]), Boolean.parseBoolean(unitStatuss[i]));
		    }
			ITaskFacade tf = (ITaskFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskFacade");
			return tf.updateTasks(fieldId, tasks);
		}
		else{
			throw new MissingParameterException();
		}
	}
}
