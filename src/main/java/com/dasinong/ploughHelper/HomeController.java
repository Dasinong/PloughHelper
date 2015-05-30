package com.dasinong.ploughHelper;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.FieldDao;
import com.dasinong.ploughHelper.model.Field;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public String home(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Welcome home! The client locale is {}.");
		
		return "";
	}
	
	
	@RequestMapping(value = "/insertField", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object insertField(HttpServletRequest request, HttpServletResponse response) {
		logger.info("InsertField: ");
		
		FieldDao fieldDao = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		Field field = new Field();
		String fieldName = (request.getParameter("fieldName")!=null) ? request.getParameter("fieldName") : "GuangZhou";
		field.setFieldName(fieldName);
		String other = (request.getParameter("other")!=null) ? request.getParameter("other") : "other";
		field.setOther(other);
		try{
			fieldDao.save(field);
			result.put("status", "200");
			result.put("field", field);
			return result;
		}
		catch(Exception e){
			result.put("status","300");
			result.put("message",e.getMessage());
			return result;
		}
	}
	
	@RequestMapping(value = "/showFieldById", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object showFieldById(HttpServletRequest request, HttpServletResponse response) {
		logger.info("showField: ");
		
		FieldDao fieldDao = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		String fieldName = (request.getParameter("fieldName")!=null) ? request.getParameter("fieldName") : "GuangZhou"; 
		try {
			Field field = fieldDao.findByFieldName(fieldName);
    		result.put("status", "200");
    	    result.put("field", field);
    	    return result;
    	}
    	catch(Exception e)
    	{
    		result.put("status", "300");
    		result.put("message", e.getMessage());
    		return result;
    	}	
	}
	
	@RequestMapping(value = "/deleteFieldById", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object deleteFieldById(HttpServletRequest request, HttpServletResponse response) {
		logger.info("DeleteFieldById: ");
		
		FieldDao fieldDao = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		String fieldName = (request.getParameter("fieldName")!=null) ? request.getParameter("fieldName") : "GuangZhou"; 
		try {
			Field field = fieldDao.findByFieldName(fieldName);
			fieldDao.delete(field);
    		result.put("status", "200");
    	    result.put("field", field);
    	    return result;
    	}
    	catch(Exception e)
    	{
    		result.put("status", "300");
    		result.put("message", e.getMessage());
    		return result;
    	}	
		

	}
}
