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

import com.dasinong.ploughHelper.dao.TaskDao;
import com.dasinong.ploughHelper.dao.TaskSpecDao;
import com.dasinong.ploughHelper.model.TaskSpec;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.TaskSpecWrapper;

@Controller
public class TaskSpecController {
	private static final Logger logger = LoggerFactory.getLogger(Test1Controller.class);
	
	
	@RequestMapping(value = "/taskSpec", produces="application/json")
	@ResponseBody
	public Object getTaskSpec(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		TaskSpecDao taskSpecDao = (TaskSpecDao) ContextLoader.getCurrentWebApplicationContext().getBean("taskSpecDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		try{
			String taskSpecId = request.getParameter("taskSpecId");
			Long taskSpecid = Long.parseLong(taskSpecId);
			TaskSpec taskspec = taskSpecDao.findById(taskSpecid);
			TaskSpecWrapper tsw = new TaskSpecWrapper(taskspec);
			result.put("respCode",200);
			result.put("message","获得任务描述");
			result.put("data", tsw);
			return result;	
		}catch(Exception e){
			result.put("respCode",500);
			result.put("message",e.getCause());
			return result;
		}
	}
}
