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

import com.dasinong.ploughHelper.dao.CropDao;
import com.dasinong.ploughHelper.dao.UserDao;
import com.dasinong.ploughHelper.dao.VarietyDao;
import com.dasinong.ploughHelper.inputParser.UserParser;
import com.dasinong.ploughHelper.model.Crop;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.UserWrapper;

@Controller
public class VarietyController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/getVarietyList",produces="application/json")
	@ResponseBody
	public Object getVariety(HttpServletRequest request, HttpServletResponse response) {
	
		CropDao cropDao = (CropDao) ContextLoader.getCurrentWebApplicationContext().getBean("cropDao");
		VarietyDao varietyDao = (VarietyDao) ContextLoader.getCurrentWebApplicationContext().getBean("vareityDao");
		HashMap<String,Object> result = new HashMap<String,Object>();
		try{
			String cropName = request.getParameter("cropName");
			Crop crop = cropDao.findByCropName(cropName);
			User user= (new UserParser(request)).getUser();
				
			UserWrapper userWrapper = new UserWrapper(user);
			result.put("data",userWrapper);
			result.put("respCode",200);
			result.put("message","注册成功");
			
			request.getSession().setAttribute("User", user);
			return result;
		}
		catch(Exception e)
		{
			result.put("respCode", "500");
			result.put("message", e.getMessage());
			return result;
		}
	}

}
