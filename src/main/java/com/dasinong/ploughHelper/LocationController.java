package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.ILocationDao;
import com.dasinong.ploughHelper.dao.LocationDao;
import com.dasinong.ploughHelper.exceptions.MissingParameterException;
import com.dasinong.ploughHelper.exceptions.UnexpectedLatAndLonException;
import com.dasinong.ploughHelper.exceptions.UserIsNotLoggedInException;
import com.dasinong.ploughHelper.exceptions.UserNotFoundInSessionException;
import com.dasinong.ploughHelper.model.Location;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.outputWrapper.LocationWrapper;
import com.dasinong.ploughHelper.util.GeoUtil;


@Controller
public class LocationController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(LocationController.class);
	
	@RequestMapping(value = "/getLocation",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object getLocation(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		User user = this.getLoginUser(request);
		Map<String,Object> result = new HashMap<String,Object>();
		if (user == null) {
			throw new UserIsNotLoggedInException();
		}
		
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String district = request.getParameter("district");
		if (province == null || city == null || country == null || district == null) {
			throw new MissingParameterException();
		}
		
		ILocationDao ld = (ILocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		List<Location> ls = (List<Location>) ld.getIdList(province, city, country,district);
		Map<String, Long> idlist = new HashMap<String, Long>();
		for(Location l:ls) {
			if (!l.getCommunity().equals("")){
				idlist.put(l.getCommunity(), l.getLocationId());
			} else {
				idlist.put(l.getDistrict(), l.getLocationId());
			}
		}
			
		result.put("respCode", 200);
		result.put("message", "获取成功");
		result.put("data", idlist);
			
		return result;
	}
	
	@RequestMapping(value = "/searchLocation",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object searchLocation(
		HttpServletRequest request, 
		HttpServletResponse response
	) throws Exception {
		User user = this.getLoginUser(request);
		Map<String,Object> result = new HashMap<String,Object>();
		if (user == null){
			throw new UserIsNotLoggedInException();
		}
		
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String country = request.getParameter("country");
		String latitude = request.getParameter("lat");
		String longitude = request.getParameter("lon");
		if (province==null || city==null || country==null || latitude==null || longitude==null){
			throw new MissingParameterException();
		}
		
		double lat = Double.parseDouble(latitude);
		double lon = Double.parseDouble(longitude);
		LocationDao ld = (LocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		List<Location> ls = (List<Location>) ld.getHibernateTemplate().find("from Location where province=? and city=? and country=?",province,city,country);
		GeoUtil geo = new GeoUtil(ls);
		Location nearest = geo.getNearLoc(lat, lon);
		LocationWrapper nearl = new LocationWrapper(nearest);
		result.put("respCode", 200);
		result.put("message", "获取成功");
		result.put("data", nearl);
			
		return result;
	}
	
	@RequestMapping(value = "/searchLocationByLatAndLon",
					method = RequestMethod.GET,
					produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Object searchLocationByLatAndLon(
		HttpServletRequest request, 
		HttpServletResponse response
	) throws Exception {
		Map<String,Object> result = new HashMap<String,Object>();
		
		String latStr = request.getParameter("lat");
		String lonStr = request.getParameter("lon");
		double lat = Double.parseDouble(latStr);
		double lon = Double.parseDouble(lonStr);
		
		LocationDao ld = (LocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		Location nearest = null;
		
		// normally you can find hundreds of locations with range = 0.01
		// and thousands of locations with range = 0.1
		// and tens of thousands of locations with range = 1
		double[] ranges = {0.01, 0.1, 1};
		for (double range : ranges) {
			List<Location> ls = (List<Location>) ld.findLocationsInRange(lat, lon, range);
			if (ls != null && ls.size() > 0) {
				GeoUtil geo = new GeoUtil(ls);
				nearest = geo.getNearLoc(lat, lon);
				break;
			}
		}
		
		if (nearest == null) {
			// This should NEVER happen!
			throw new UnexpectedLatAndLonException(lat, lon);
		}
		
		LocationWrapper locWrapper = new LocationWrapper(nearest);
		result.put("respCode", 200);
		result.put("message", "获取成功");
		result.put("data", locWrapper);
		
		return result;
	}
}
