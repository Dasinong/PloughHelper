package com.dasinong.ploughHelper;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasinong.ploughHelper.datapool.AllMonitorLocation;
import com.dasinong.ploughHelper.model.User;


//Add some short cut here to check/sync frontend and backend status
@Controller
public class ShortCutController {
	private static final Logger logger = LoggerFactory.getLogger(ShortCutController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/checkIsLogin", produces="application/json")
	@ResponseBody
	public Object checkLogin(HttpServletRequest request, HttpServletResponse response){
		HashMap<String,Object> result = new HashMap<String,Object>();
		User user = (User) request.getSession().getAttribute("User");
		if (user==null){
			result.put("respCode", 100);
			result.put("message","尚未登陆");
			result.put("data", false);
			return result;
		}
		else{
			result.put("respCode", 200);
			result.put("message","用户已登陆");
			result.put("data",true);
			return result;
		}
		
	}
	
	
	@RequestMapping(value = "/getMonLocIdByGeo", produces="application/json")
	@ResponseBody
	public Object getMonLocIdByGeo(HttpServletRequest request, HttpServletResponse response){
		HashMap<String,Object> result = new HashMap<String,Object>();
		double lat;
		double lon;
		try{
			lat = Double.parseDouble(request.getParameter("lat"));
			lon = Double.parseDouble(request.getParameter("lon"));
		}catch(Exception e){
			result.put("respCode", 300);
			result.put("message", "输入参数或参数格式错误");
			return result;
		}
		try {
			Integer mlId = AllMonitorLocation.getLocation().getNearest(lat, lon);
			result.put("respCode", 200);
			result.put("message", "获得监控地址成功");
			result.put("data", mlId);
			return result;
		} catch (Exception e) {
			result.put("respCode", 405);
			result.put("message", "初始化检测地址列表出错");
			return result;
		}
	}
	
	
	@RequestMapping(value = "/loadLoc", produces="application/json")
	@ResponseBody
	public Object loadLoc(HttpServletRequest request, HttpServletResponse response){
			com.dasinong.ploughHelper.datapool.AllLocation.getLocation();
			return "OK";
	}
	
}
