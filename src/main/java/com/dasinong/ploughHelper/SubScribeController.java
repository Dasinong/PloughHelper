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

import com.dasinong.ploughHelper.facade.ISubScribeFacade;
import com.dasinong.ploughHelper.facade.SubScribeFacade;
import com.dasinong.ploughHelper.model.User;

@Controller
public class SubScribeController {
	
	private static final Logger logger = LoggerFactory.getLogger(SubScribeController.class);

	@RequestMapping(value = "/insertSubScribeList", produces="application/json")
	@ResponseBody
	public Object insertSubScribeList(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		String targetName;
		String cellphone;
		String province;
		String city;
		String country;
		String district;
		double area;
		Long cropId;
		boolean isAgriWeather;
		boolean isNatAlter;
		boolean isRiceHelper;
		try{
			targetName =  request.getParameter("targetName");
			cellphone =  request.getParameter("cellphone");
			province =  request.getParameter("province");
			city =  request.getParameter("city");
			country =  request.getParameter("country");
			district =  request.getParameter("district");
			area =  Double.parseDouble(request.getParameter("area"));
			cropId =  Long.parseLong(request.getParameter("cropId"));
			isAgriWeather =  Boolean.parseBoolean(request.getParameter("isAgriWeather"));
			isNatAlter =  Boolean.parseBoolean(request.getParameter("isNatAlter"));
			isRiceHelper =  Boolean.parseBoolean(request.getParameter("isRiceHelper"));
		}
	    catch(Exception e){
	    	result.put("respCode",300);
	    	result.put("message","参数错误");
	    	return result;
	    }
		try{
			ISubScribeFacade ssf = new SubScribeFacade();
			return ssf.insertSubScribeList(user, targetName, cellphone, province, city, country, 
						district, area, cropId, isAgriWeather, isNatAlter, isRiceHelper);
		}catch(Exception e){
			result.put("respCode", 500);
			result.put("message", e.getCause());
			return result;
		}
	}
	
	@RequestMapping(value = "/getSubScribeLists", produces="application/json")
	@ResponseBody
	public Object getSubScribeLists(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		try{
			ISubScribeFacade ssf = new SubScribeFacade();
			return ssf.getSubScribeLists(user);
		}catch(Exception e){
			result.put("respCode", 500);
			result.put("message", e.getCause());
			return result;
		}
	}
	
	
	@RequestMapping(value = "/loadSubScribeList", produces="application/json")
	@ResponseBody
	public Object loadSubScribeList(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		Long id;
		try{
			id =  Long.parseLong(request.getParameter("id"));
		}
	    catch(Exception e){
	    	result.put("respCode",300);
	    	result.put("message","参数错误");
	    	return result;
	    }
		
		try{
			ISubScribeFacade ssf = new SubScribeFacade();
			return ssf.loadSubScribeList(id);
		}catch(Exception e){
			result.put("respCode", 500);
			result.put("message", e.getCause());
			return result;
		}
	}
	
	@RequestMapping(value = "/upateSubScribeList", produces="application/json")
	@ResponseBody
	public Object updateSubScribeList(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		Long id;
		String targetName;
		String cellphone;
		String province;
		String city;
		String country;
		String district;
		double area;
		Long cropId;
		boolean isAgriWeather;
		boolean isNatAlter;
		boolean isRiceHelper;
		try{
			id =  Long.parseLong(request.getParameter("id"));
			targetName =  request.getParameter("targetName");
			cellphone =  request.getParameter("cellphone");
			province =  request.getParameter("province");
			city =  request.getParameter("city");
			country =  request.getParameter("country");
			district =  request.getParameter("district");
			area =  Double.parseDouble(request.getParameter("area"));
			cropId =  Long.parseLong(request.getParameter("cropId"));
			isAgriWeather =  Boolean.parseBoolean(request.getParameter("isAgriWeather"));
			isNatAlter =  Boolean.parseBoolean(request.getParameter("isNatAlter"));
			isRiceHelper =  Boolean.parseBoolean(request.getParameter("isRiceHelper"));
		}
	    catch(Exception e){
	    	result.put("respCode",300);
	    	result.put("message","参数错误");
	    	return result;
	    }
		
		try{
			ISubScribeFacade ssf = new SubScribeFacade();
			return ssf.updateSubScribeList(id, user, targetName, cellphone, province, city, country,
					district, area, cropId, isAgriWeather, isNatAlter, isRiceHelper);
		}catch(Exception e){
			result.put("respCode", 500);
			result.put("message", e.getCause());
			return result;
		}
	}
	
	
	
	@RequestMapping(value = "/deleteSubScribeList", produces="application/json")
	@ResponseBody
	public Object deleteSubScribeList(HttpServletRequest request, HttpServletResponse response) {

		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		Long id;
		try{
			id =  Long.parseLong(request.getParameter("id"));
		}
	    catch(Exception e){
	    	result.put("respCode",300);
	    	result.put("message","参数错误");
	    	return result;
	    }
		
		try{
			ISubScribeFacade ssf = new SubScribeFacade();
			return ssf.deleteSubScribeList(id);
		}catch(Exception e){
			result.put("respCode", 500);
			result.put("message", e.getCause());
			return result;
		}
	}
	

}
