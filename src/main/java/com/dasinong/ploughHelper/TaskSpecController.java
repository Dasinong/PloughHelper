package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.exceptions.InvalidParameterException;
import com.dasinong.ploughHelper.facade.ITaskSpecFacade;
import com.dasinong.ploughHelper.outputWrapper.StepWrapper;

@Controller
public class TaskSpecController extends RequireUserLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskSpecController.class);
	
	ITaskSpecFacade tsf;
	
	@RequestMapping(value = "/getTaskSpec", produces="application/json")
	@ResponseBody
	public Object getTaskSpec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		tsf =  (ITaskSpecFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecFacade");
		Long taskSpecId;
		try{
			taskSpecId = Long.parseLong(request.getParameter("taskSpecId"));
		}
		catch(Exception e){
			throw new InvalidParameterException("taskSpecId","Long");
		}

		return tsf.getTaskSpec(taskSpecId);

	}
	
	@RequestMapping(value = "/getSteps", produces="application/json")
	@ResponseBody
	public Object getSteps(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		tsf =  (ITaskSpecFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecFacade");
		Long taskSpecId;
		Long fieldId;
		try{
			taskSpecId = Long.parseLong(request.getParameter("taskSpecId"));
		}
		catch(Exception e){
			throw new InvalidParameterException("taskSpecId","Long");
		}
		
		//fieldId is optional, default to 0
		try{
			fieldId = Long.parseLong(request.getParameter("fieldId"));
		}
		catch(Exception e){
			fieldId=0L;
		}
		
		List<StepWrapper> sw = tsf.getSteps(taskSpecId,fieldId);
		result.put("respCode",200);
		result.put("message","任务列表获取成功");
		result.put("data", sw);
		return result;
	}
}
