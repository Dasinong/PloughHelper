package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasinong.ploughHelper.model.User;


@Controller
public class GeoController {

	
	@RequestMapping(value = "/getNearUser", produces="application/json")
	@ResponseBody
	public Object getNearUser(HttpServletRequest request, HttpServletResponse response) {
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		
		try{
			String lat = request.getParameter("lat");
			String lon = request.getParameter("lon");
			if (lat==null || lon==null){
				result.put("respCode", 300);
				result.put("message", "缺少参数");
			}
			
			result.put("respCode", 200);
			result.put("message", "获得附近农户数");
			Random rnd = new Random();
			result.put("data",rnd.nextInt(100)+50);
			return result;
		}
		catch(Exception e)
		{
			result.put("respCode", 500);
			result.put("respDes", e.getCause().getMessage());
			return result;
		}
	}
}
