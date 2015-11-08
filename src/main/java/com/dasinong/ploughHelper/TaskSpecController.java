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

import com.dasinong.ploughHelper.facade.ITaskSpecFacade;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.StepWrapper;

@Controller
public class TaskSpecController extends RequireUserLoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(TaskSpecController.class);
	
	ITaskSpecFacade tsf;
	
	@RequestMapping(value = "/getTaskSpec", produces="application/json")
	@ResponseBody
	public Object getTaskSpec(HttpServletRequest request, HttpServletResponse response) {
		HashMap<String,Object> result = new HashMap<String,Object>();
		
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
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		tsf =  (ITaskSpecFacade) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecFacade");
		Long taskSpecId;
		Long fieldId;
		try{
			taskSpecId = Long.parseLong(request.getParameter("taskSpecId"));
		}
		catch(Exception e){
			result.put("respCode",321);
			result.put("message","taskSpecId参数格式或内容错误");
			return result;
		}
		
		
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
