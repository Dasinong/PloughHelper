package com.dasinong.ploughHelper.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.dao.IFieldDao;
import com.dasinong.ploughHelper.dao.ILocationDao;
import com.dasinong.ploughHelper.dao.IProverbDao;
import com.dasinong.ploughHelper.dao.ISystemMessageDao;
import com.dasinong.ploughHelper.datapool.AllMonitorLocation;
import com.dasinong.ploughHelper.laonong.LaoNong;
import com.dasinong.ploughHelper.laonong.LaoNongManager;
import com.dasinong.ploughHelper.laonong.ProverbDataSource;
import com.dasinong.ploughHelper.laonong.SystemMessageDataSource;
import com.dasinong.ploughHelper.laonong.WeatherAlertDataSource;
import com.dasinong.ploughHelper.model.LaoNongType;
import com.dasinong.ploughHelper.model.Proverb;
import com.dasinong.ploughHelper.model.SystemMessage;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.weather.GetWeatherAlert;
import com.dasinong.ploughHelper.weather.WeatherAlert;
import com.dasinong.ploughHelper.weather.AgriDisForcast;
import com.dasinong.ploughHelper.weather.AllAgriDisForcast;

public class LaoNongFacade implements ILaoNongFacade {

	@Override
	public Object getLaoNongs_DEPRECATED(double lat, double lon, User user) {
		try {
			Long mlId = AllMonitorLocation.getInstance().getNearest(lat, lon);
			return getLaoNongs_DEPRECATED(mlId, user);
		} catch (Exception e) {
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("respCode", 405);
			result.put("message", "初始化检测地址列表出错");
			return result;
		}
	}

	@Override
	public Object getLaoNongs_DEPRECATED(Long areaId, User user) {
		ISystemMessageDao smDao = (ISystemMessageDao) ContextLoader.getCurrentWebApplicationContext().getBean("systemMessageDao");
		ILocationDao locDao = (ILocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		IProverbDao proverbDao = (IProverbDao) ContextLoader.getCurrentWebApplicationContext().getBean("proverbDao");
		
		LaoNongManager manager = new LaoNongManager();
		manager.addSource(new WeatherAlertDataSource());
		manager.addSource(new SystemMessageDataSource(smDao, locDao));
		manager.addSource(new ProverbDataSource(proverbDao));
		
		List<LaoNong> laonongs = manager.genLaoNongs(user, areaId);

		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put("respCode", 200);
		result.put("message", "获得老农成功");
		// data只包含第一条。这是为了兼容之前的android版本
		if (laonongs.size() > 0) {
			result.put("data", laonongs.get(0));
		}
		result.put("newdata", laonongs);
		return result;
	}

	@Override
	public Object getLaoNongs(Long areaId, User user) {
		ISystemMessageDao smDao = (ISystemMessageDao) ContextLoader.getCurrentWebApplicationContext().getBean("systemMessageDao");
		ILocationDao locDao = (ILocationDao) ContextLoader.getCurrentWebApplicationContext().getBean("locationDao");
		IProverbDao proverbDao = (IProverbDao) ContextLoader.getCurrentWebApplicationContext().getBean("proverbDao");
		
		LaoNongManager manager = new LaoNongManager();
		manager.addSource(new WeatherAlertDataSource());
		manager.addSource(new SystemMessageDataSource(smDao, locDao));
		manager.addSource(new ProverbDataSource(proverbDao));
		
		List<LaoNong> laonongs = manager.genLaoNongs(user, areaId);
		
		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("laonongs", laonongs);
		result.put("respCode", 200);
		result.put("message", "获得老农成功");
		result.put("data", data);
		return result;
	}

	@Override
	public Object getLaoNongs(double lat, double lon, User user) {
		Long mlId = AllMonitorLocation.getInstance().getNearest(lat, lon);
		return getLaoNongs(mlId, user);
	}
}
