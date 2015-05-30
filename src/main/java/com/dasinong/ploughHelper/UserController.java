package com.dasinong.ploughHelper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.CropDao;
import com.dasinong.ploughHelper.dao.FieldDao;
import com.dasinong.ploughHelper.dao.UserDao;
import com.dasinong.ploughHelper.dao.VarietyDao;
import com.dasinong.ploughHelper.inputParser.UserParser;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.Variety;


@Controller
public class UserController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/regUser",produces="application/json")
	@ResponseBody
	public Object reg(HttpServletRequest request, HttpServletResponse response) {
	
		UserDao userdao = (UserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			User user= (new UserParser(request)).getUser();
			userdao.save(user);
						
			result.put("test",user);
			result.put("status",200);
			
			return result;
		}
		catch(Exception e)
		{
			result.put("status", "500");
			result.put("cause", e.getCause());
			return result;
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET,produces="application/json")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response) {
	
		FieldDao fieldDao = (FieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		VarietyDao varietyDao = (VarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("varietyDao");
		CropDao cropDao = (CropDao) ContextLoader.getCurrentWebApplicationContext().getBean("cropDao");
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			Crop crop = new Crop();
			crop.setCropName("Ë®µ¾");
			crop.setOther("Big father");
			cropDao.save(crop);
			
			Variety variety1 = new Variety();
			variety1.setVarietyName("TestVariety1");
			variety1.setCrop(crop);
			variety1.setOther("This is for test1");
			varietyDao.save(variety1);
		
			Variety variety2 = new Variety();
			variety2.setVarietyName("TestVariety2");
			variety2.setCrop(crop);
			variety2.setOther("This is for test2.Two field assotication with this one");
			varietyDao.save(variety2);
				
		    Field field = new Field();
		    field.setFieldName("Test field 1");
		    field.setVariety(variety1);
		    field.setOther("Associate with variety1");
		    variety1.getFields().add(field);
		    fieldDao.save(field);
		
		    
		    field = new Field();
		    field.setFieldName("Test field 2");
		    field.setVariety(variety2);
		    field.setOther("Associate with variety2");
		    variety2.getFields().add(field);
		    fieldDao.save(field);
		    
		    field = new Field();
		    field.setFieldName("Test field 3");
		    field.setVariety(variety2);
		    field.setOther("Associate with variety2");
		    variety2.getFields().add(field);
		    fieldDao.save(field);
		    
		    
			System.out.println("Done");
			result.put("test","testoutputcheck");
			result.put("status",200);
			
		return result;
		}
		catch(Exception e)
		{
			result.put("status", "500");
			result.put("cause", e.getCause());
			return result;
		}
	}

}
