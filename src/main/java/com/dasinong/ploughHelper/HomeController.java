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
		IFieldDao fieldDao = (IFieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		
		String fieldId = request.getParameter("fieldId");
		String taskIds = request.getParameter("taskIds");
		String taskStatuss = request.getParameter("taskStatuss");
		String taskId =  request.getParameter("taskId");
		String taskStatus =  request.getParameter("taskStatus");
		
		try{
			if (fieldId==null){
				result.put("respCode", 302);
				result.put("message", "必须输入fieldId" );
				return result;
			}
			Long fieldid = Long.parseLong(fieldId);
			Field field = fieldDao.findById(fieldid);
			if (taskIds==null && taskStatuss==null &&taskId!=null && taskStatus!=null){
				Long id = Long.parseLong(taskId);
				boolean status = Boolean.parseBoolean(taskStatus);
				//TODO: add task authority check;
				Task task = field.getTasks().get(id);	
				task.setTaskStatus(status);
				fieldDao.update(field);
				result.put("respCode", 200);
				result.put("message", "更新任务成功");
				return result;
			}
			else if (taskIds!=null && taskStatuss!=null &&taskId==null && taskStatus==null){
			    String[] unitIds = taskIds.split(",");
			    String[] unitStatuss = taskStatuss.split(",");
			    if (unitIds.length!=unitStatuss.length){
			    	result.put("respCode", 301);
					result.put("message", "输入任务表长度不匹配" );
					return result;
			    }
			    for (int i=0;i<unitIds.length;i++){
			    	Long id = Long.parseLong(unitIds[i]);
					boolean status = Boolean.parseBoolean(unitStatuss[i]);
					//TODO: add task authority check;
					Task task = field.getTasks().get(id);	
					task.setTaskStatus(status);
			    }
			    fieldDao.update(field);
				result.put("respCode", 200);
				result.put("message", "更新任务成功");
				return result;
			}
			else{
				result.put("respCode", 300);
				result.put("message", "输入参数错误" );
				return result;
			}
			
		}catch(Exception e){
			result.put("respCode", 500);
			result.put("message", e.getCause() );
			return result;
		}
		
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
