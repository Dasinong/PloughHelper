package com.dasinong.ploughHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.ITaskSpecDao;
import com.dasinong.ploughHelper.facade.HomeFacade;
import com.dasinong.ploughHelper.facade.IHomeFacade;
import com.dasinong.ploughHelper.facade.ISoilFacade;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Task;
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
		HashMap<String,String> duanzi = new HashMap<String,String>();
		duanzi.put("id", "1");
		duanzi.put("title", "一个大学而已");
		duanzi.put("content", "高考成绩不理想真无所谓，你奋斗过，努力过，拼搏过，就不必太在乎结果。考的分低，不说明你蠢，但为此离家出走、自残自杀，就是蠢了。高考，只是一段分数、一个大学而已，命运哪能让它决定？傻逼的人生觉得分低无法解释，彪悍的人生是再考一次！ ");

		result.put("respcode", 200);
		result.put("message", "获取段子成功");
		result.put("data",duanzi);
		return result;
	}

}
