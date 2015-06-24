package com.dasinong.ploughHelper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.ITaskSpecDao;
import com.dasinong.ploughHelper.facade.IFieldFacade;
import com.dasinong.ploughHelper.model.User;


@Controller
public class FieldController {
	private static final Logger logger = LoggerFactory.getLogger(Test1Controller.class);
	
	@Autowired
	private ITaskSpecDao taskSpecDao;
	
	
	@RequestMapping(value = "/createField", produces="application/json")
	@ResponseBody
	public Object createField(HttpServletRequest request, HttpServletResponse response)  {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
	    
		String fieldName; 
        Date startDate;
        
        
    	boolean isActive;
       	boolean seedingortransplant;
       	double area;
       	long locationId;
       	long varietyId;
       	long currentStageId;
       	
		try{
			fieldName =  request.getParameter("fieldName");
			isActive =  Boolean.parseBoolean(request.getParameter("isActive"));
			seedingortransplant = Boolean.parseBoolean(request.getParameter("seedingortransplant"));
			area = Double.parseDouble(request.getParameter("area"));
			try{
				startDate = new Date(Long.parseLong(request.getParameter("startDate")));
			}
			catch(Exception e){
				startDate = new Date(request.getParameter("startDate"));
			}
			
			locationId = Long.parseLong(request.getParameter("locationId"));
		    varietyId =  Long.parseLong(request.getParameter("varietyId"));
            currentStageId = Long.parseLong(request.getParameter("currentStageID"));
		}
		catch(Exception e){
	    	result.put("respCode",300);
			result.put("message","参数错误");
			return result;
		}
  	    
	    IFieldFacade ff =  (IFieldFacade) ContextLoader.getCurrentWebApplicationContext().getBean("fieldFacade");
		return ff.createField(user, fieldName,startDate,isActive,seedingortransplant,area,locationId,varietyId,currentStageId);

	}
	
	
	@RequestMapping(value = "/searchNearUser", produces="application/json")
	@ResponseBody
	public Object searchNearUser(HttpServletRequest request, HttpServletResponse response)  {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
	    
		try{
			Random ma = new Random();
			result.put("respCode", 200);
			result.put("message", "附近农户数");
			result.put("data", ma.nextInt(200));
			return result;
		} catch (Exception e) {
			result.put("respCode",500);
			result.put("message", e.getCause());
			return result;
		}
	}
		
}
