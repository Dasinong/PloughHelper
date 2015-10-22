package com.dasinong.ploughHelper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.servlet.ModelAndView;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.ILocationDao;
import com.dasinong.ploughHelper.dao.IUserDao;
import com.dasinong.ploughHelper.dao.IWeatherSubscriptionDao;
import com.dasinong.ploughHelper.exceptions.ParameterMissingException;
import com.dasinong.ploughHelper.exceptions.ResourceNotFoundException;
import com.dasinong.ploughHelper.exceptions.UserNotFoundInSessionException;
import com.dasinong.ploughHelper.model.Field;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.WeatherSubscription;
import com.dasinong.ploughHelper.model.WeatherSubscriptionType;
import com.dasinong.ploughHelper.weather.AllLocation;

@Controller
public class WeatherSubscriptionController extends BaseController {

	@RequestMapping(value = "/weatherSubscriptions", 
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getWeatherSubscriptions(
		HttpServletRequest request, 
		HttpServletResponse response
	) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		
		IUserDao userdao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		User user = userdao.findById(463L);
		// User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			throw new UserNotFoundInSessionException();
		}
		
		IWeatherSubscriptionDao weatherSubsDao = 
			(IWeatherSubscriptionDao) ContextLoader.getCurrentWebApplicationContext().getBean("weatherSubscriptionDao");
		
		List<WeatherSubscription> subs = weatherSubsDao.findByUserId(user.getUserId());
		result.put("respCode", 200);
		result.put("respMsg", "获取成功");
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
		
		// IUserDao userdao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		// User user = userdao.findById(463L);
		User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			throw new UserNotFoundInSessionException();
		}
		
		IWeatherSubscriptionDao weatherSubsDao = 
			(IWeatherSubscriptionDao) ContextLoader.getCurrentWebApplicationContext().getBean("weatherSubscriptionDao");
		
		WeatherSubscription subs = weatherSubsDao.findById(id);
		if (subs == null || !subs.getUserId().equals(user.getUserId())) {
			throw new ResourceNotFoundException(id, "WeatherSubscription");
		}
		
		result.put("respCode", 200);
		result.put("respMsg", "获取成功");
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
		
		IUserDao userdao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		User user = userdao.findById(463L);
		// User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			throw new UserNotFoundInSessionException();
		}
		
		IWeatherSubscriptionDao weatherSubsDao = 
			(IWeatherSubscriptionDao) ContextLoader.getCurrentWebApplicationContext().getBean("weatherSubscriptionDao");
		
		WeatherSubscription subs = weatherSubsDao.findById(id);
		if (subs == null || !subs.getUserId().equals(user.getUserId())) {
			throw new ResourceNotFoundException(id, "WeatherSubscription");
		}
		
		weatherSubsDao.delete(subs);
		result.put("respCode", 200);
		result.put("respMsg", "删除成功");
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
		
		IUserDao userdao = (IUserDao) ContextLoader.getCurrentWebApplicationContext().getBean("userDao");
		User user = userdao.findById(463L);
		// User user = (User) request.getSession().getAttribute("User");
		if (user == null) {
			throw new UserNotFoundInSessionException();
		}
		
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
		AllLocation allLocation = AllLocation.getLocation();
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
	    result.put("respMsg", "创建成功");
	    result.put("data", subs);
	    
	    return result;
	}
	
	@RequestMapping(value = "/internal/fieldWeatherSubscriptions", 
			method = RequestMethod.POST, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object createWeatherSubscriptionsForFields(
		HttpServletRequest request, 
		HttpServletResponse response
	) throws IOException {
		Map<String,Object> result = new HashMap<String,Object>();
		
		IFieldDao fieldDao = (IFieldDao) ContextLoader.getCurrentWebApplicationContext().getBean("fieldDao");
		List<Field> fields = fieldDao.findAll();
		IWeatherSubscriptionDao weatherSubsDao = 
				(IWeatherSubscriptionDao) ContextLoader.getCurrentWebApplicationContext().getBean("weatherSubscriptionDao");
		
		int i = 0;
		for (Field field : fields) {
			Location loc = field.getLocation();
			WeatherSubscription subs = new WeatherSubscription();
		    subs.setLocationId(loc.getLocationId());
		    subs.setMonitorLocationId(field.getMonitorLocationId());
		    subs.setLocationName(loc.toString());
		    subs.setUserId(field.getUser().getUserId());
		    subs.setType(WeatherSubscriptionType.FIELD);
		    weatherSubsDao.save(subs);
		 }
		
		result.put("respCode", 200);
	    result.put("respMsg", "创建成功");
	    
	    return result;
	}

}
