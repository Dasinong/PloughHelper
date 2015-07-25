package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.ILocationDao;
import com.dasinong.ploughHelper.dao.LocationDao;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.LocationWrapper;
import com.dasinong.ploughHelper.util.GeoUtil;


@Controller
public class LocationController {
	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
	
	@RequestMapping(value = "/getLocation", produces="application/json")
	@ResponseBody
	public Object getLocation(HttpServletRequest request, HttpServletResponse response)
	{
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		try{
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			String district = request.getParameter("district");
			if (province==null || city==null || country==null || district==null){
				result.put("respCode", 300);
				result.put("message","缺失参数");
				return result;
			}
		
			ILocationDao ld = (ILocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
			List<Location> ls =(List<Location>) ld.getIdList(province, city, country,district);
			Map<String,Long> idlist = new HashMap<String,Long>();
			for(Location l:ls){
				if (!l.getCommunity().equals("")){
					idlist.put(l.getCommunity(),l.getLocationId());
				}else{
					idlist.put(l.getDistrict(), l.getLocationId());
				}
			}
			result.put("respCode", 200);
			result.put("message", "获取成功");
			result.put("data", idlist);
			
			return result;
		}catch(Exception e){
			result.put("respCode",500);
			result.put("message",e.getCause());
			return result;
		}
	}
	
	
	
	@RequestMapping(value = "/searchLocation", produces="application/json")
	@ResponseBody
	public Object searchLocation(HttpServletRequest request, HttpServletResponse response)
	{
		User user = (User) request.getSession().getAttribute("User");
		Map<String,Object> result = new HashMap<String,Object>();
		if (user==null){
			result.put("respCode",100);
			result.put("message","用户尚未登陆");
			return result;
		}
		try{
			String province = request.getParameter("province");
			String city = request.getParameter("city");
			String country = request.getParameter("country");
			String latitude = request.getParameter("lat");
			String longitude = request.getParameter("lon");
			if (province==null || city==null || country==null || latitude==null || longitude==null){
				result.put("respCode", 300);
				result.put("message","缺失参数");
				return result;
			}
		
			double lat = Double.parseDouble(latitude);
			double lon = Double.parseDouble(longitude);
			LocationDao ld = (LocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
			List<Location> ls =(List<Location>) ld.getHibernateTemplate().find("from Location where province=? and city=? and country=?",province,city,country);
			GeoUtil geo = new GeoUtil(ls);
			Location nearest = geo.getNearLoc(lat, lon);
			LocationWrapper nearl = new LocationWrapper(nearest);
			result.put("respCode", 200);
			result.put("message", "获取成功");
			result.put("data", nearl);
			
			return result;
		}catch(Exception e){
			result.put("respCode",500);
			result.put("message",e.getCause());
			return result;
		}
	}
}
