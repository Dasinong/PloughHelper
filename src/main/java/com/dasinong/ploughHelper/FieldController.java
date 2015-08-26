package com.dasinong.ploughHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.dasinong.ploughHelper.outputWrapper.FieldWrapper;
import com.dasinong.ploughHelper.outputWrapper.SubStageWrapper;


@Controller
public class FieldController {
	private static final Logger logger = LoggerFactory.getLogger(FieldController.class);
	
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
       	String currentStageId;
       	String yield;
       	
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
            currentStageId = request.getParameter("currentStageId");
            yield = request.getParameter("yield");
            
		}
		catch(Exception e){
	    	result.put("respCode",300);
			result.put("message","参数错误");
			return result;
		}
  	    
	    IFieldFacade ff =  (IFieldFacade) ContextLoader.getCurrentWebApplicationContext().getBean("fieldFacade");
		try{
			FieldWrapper fw = ff.createField(user, fieldName, startDate, isActive, seedingortransplant, area, 
					locationId, varietyId, currentStageId, yield);
			result.put("respCode", 200);
			result.put("message", "添加田地成功");
			result.put("data",fw);
			return result;
		}
		catch (Exception e) {
			result.put("respCode",500);
			result.put("message", e.getCause());
			return result;
		}
	    
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
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Calendar ccal = Calendar.getInstance();
			Calendar scal = Calendar.getInstance();
			scal.setTime(sdf.parse("20150511"));
			int difference = (int) Math.floor((ccal.getTimeInMillis()-scal.getTimeInMillis())/60000000L);
			result.put("respCode", 200);
			result.put("message", "附近农户数");
			result.put("data", difference);
			return result;
		} catch (Exception e) {
			result.put("respCode",500);
			result.put("message", e.getCause());
			return result;
		}
	}
	
	
	@RequestMapping(value = "/changeStage", produces="application/json")
	@ResponseBody
	public Object changeStage(HttpServletRequest request, HttpServletResponse response)  {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
	    
		Long fieldId; 
		Long currentStageId;
		
		try{
			fieldId = Long.parseLong(request.getParameter("fieldId"));
			currentStageId = Long.parseLong(request.getParameter("currentStageId"));
		}
		catch(Exception e){
	    	result.put("respCode",300);
			result.put("message","参数错误");
			return result;
		}
  	    
	    IFieldFacade ff =  (IFieldFacade) ContextLoader.getCurrentWebApplicationContext().getBean("fieldFacade");
		FieldWrapper fw = ff.changeField(fieldId,currentStageId);
		result.put("respCode",200);
		result.put("message","更换阶段成功");
		result.put("data", fw);
		return result;
	}
	
	
	@RequestMapping(value = "/getStages", produces="application/json")
	@ResponseBody
	public Object getStages(HttpServletRequest request, HttpServletResponse response)  {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
	    
		Long varietyId;
		try{
			varietyId = Long.parseLong(request.getParameter("varietyId"));
		}
		catch(Exception e){
	    	result.put("respCode",300);
			result.put("message","参数错误");
			return result;
		}
		IFieldFacade ff =  (IFieldFacade) ContextLoader.getCurrentWebApplicationContext().getBean("fieldFacade");

		List<SubStageWrapper> ssw = ff.getStages(varietyId);
		result.put("respCode",200);
		result.put("message","获得阶段列表成功");
		result.put("data", ssw);
		return result;
	}
	
	
	
	
	public static void main(String[] args) throws ParseException{
		
	}
		
}
