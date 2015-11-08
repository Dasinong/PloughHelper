package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;


import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.ILocationDao;
import com.dasinong.ploughHelper.dao.IWeatherSubscriptionDao;
import com.dasinong.ploughHelper.datapool.AllMonitorLocation;
import com.dasinong.ploughHelper.exceptions.ParameterMissingException;
import com.dasinong.ploughHelper.exceptions.ResourceNotFoundException;
import com.dasinong.ploughHelper.exceptions.UserNotFoundInSessionException;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.WeatherSubscription;
import com.dasinong.ploughHelper.model.WeatherSubscriptionType;

@Controller
public class WeatherSubscriptionController extends RequireUserLoginController {

	@RequestMapping(value = "/weatherSubscriptions", 
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getWeatherSubscriptions(
		HttpServletRequest request, 
		HttpServletResponse response
	) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		User user = this.getLoginUser(request);
		
		IWeatherSubscriptionDao weatherSubsDao = 
			(IWeatherSubscriptionDao) ContextLoader.getCurrentWebApplicationContext().getBean("weatherSubscriptionDao");
		
		List<WeatherSubscription> subs = weatherSubsDao.findByUserId(user.getUserId());
		result.put("respCode", 200);
		result.put("message", "获取成功");
		result.put("data", subs);
		return result;
	}
	
	@RequestMapping(value = "/weatherSubscriptions/{id}", 
					method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getWeatherSubscription(
		HttpServletRequest request, 
		HttpServletResponse response,
		@PathVariable Long id
	) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		User user = this.getLoginUser(request);
		
		IWeatherSubscriptionDao weatherSubsDao = 
			(IWeatherSubscriptionDao) ContextLoader.getCurrentWebApplicationContext().getBean("weatherSubscriptionDao");
		
		WeatherSubscription subs = weatherSubsDao.findById(id);
		if (subs == null || !subs.getUserId().equals(user.getUserId())) {
			throw new ResourceNotFoundException(id, "WeatherSubscription");
		}
		
		result.put("respCode", 200);
		result.put("message", "获取成功");
		result.put("data", subs);
		return result;
	}
	
	@RequestMapping(value = "/weatherSubscriptions/{id}", 
					method = RequestMethod.DELETE, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object deleteWeatherSubscription(
		HttpServletRequest request, 
		HttpServletResponse response,
		@PathVariable Long id
	) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		User user = this.getLoginUser(request);
		
		IWeatherSubscriptionDao weatherSubsDao = 
			(IWeatherSubscriptionDao) ContextLoader.getCurrentWebApplicationContext().getBean("weatherSubscriptionDao");
		
		WeatherSubscription subs = weatherSubsDao.findById(id);
		if (subs == null || !subs.getUserId().equals(user.getUserId())) {
			throw new ResourceNotFoundException(id, "WeatherSubscription");
		}
		
		weatherSubsDao.delete(subs);
		result.put("respCode", 200);
		result.put("message", "删除成功");
		return result;
	}
	
	@RequestMapping(value = "/reorderWeatherSubscriptions",
					method = RequestMethod.POST,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object reorderWeatherSubscriptions(
		HttpServletRequest request, 
		HttpServletResponse response,
		@RequestParam("ids[]") Long[] ids
	) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		User user = this.getLoginUser(request);
	
		IWeatherSubscriptionDao weatherSubsDao = 
				(IWeatherSubscriptionDao) ContextLoader.getCurrentWebApplicationContext().getBean("weatherSubscriptionDao");
		weatherSubsDao.updateOrdering(ids);
		
		result.put("respCode", 200);
	    result.put("message", "排序成功");
	    
	    return result; 
	}
	
	@RequestMapping(value = "/weatherSubscriptions", 
					method = RequestMethod.POST, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object createWeatherSubscription(
		HttpServletRequest request, 
		HttpServletResponse response
	) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		User user = this.getLoginUser(request);
		
		Long locationId = 0L;
		String locationIdStr = request.getParameter("locationId");
		if (locationIdStr == null || "".equals(locationIdStr)) {
			throw new ParameterMissingException("locationId");
		} else {
			locationId = Long.parseLong(locationIdStr);
		}
		
		ILocationDao locDao = (ILocationDao) 
			ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		Location loc = locDao.findById(locationId);
		if (loc == null) {
			throw new ResourceNotFoundException(locationId, "location");
		}
		AllMonitorLocation allLocation = AllMonitorLocation.getInstance();
		Long monitorLocationId = (long) allLocation.getNearest(loc.getLatitude(), loc.getLongtitude());
		
		String locationName = loc.toString();
		
	    WeatherSubscription subs = new WeatherSubscription();
	    subs.setLocationId(locationId);
	    subs.setMonitorLocationId(monitorLocationId);
	    subs.setLocationName(locationName);
	    subs.setUserId(user.getUserId());
	    subs.setType(WeatherSubscriptionType.NORMAL);
	    
	    IWeatherSubscriptionDao weatherSubsDao = 
			(IWeatherSubscriptionDao) ContextLoader.getCurrentWebApplicationContext().getBean("weatherSubscriptionDao");
			
	    weatherSubsDao.save(subs);
	    
	    result.put("respCode", 200);
	    result.put("message", "创建成功");
	    result.put("data", subs);
	    
	    return result;
	}

}
