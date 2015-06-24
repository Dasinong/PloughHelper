package com.dasinong.ploughHelper.facade;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.dasinong.ploughHelper.weather.All24h;
import com.dasinong.ploughHelper.weather.All7d;
import com.dasinong.ploughHelper.weather.GetLiveWeather;

public class WeatherFacade {
	public Object getWeather(Integer areaId) throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException{
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put("respcode", 200);
		result.put("message", "获取成功");
		
		GetLiveWeather glw = new GetLiveWeather(areaId.toString());
		result.put("current", glw.getLiveWeather());
		if (All24h.get24h().get24h(areaId)!=null){
			result.put("12h", All24h.get24h().get24h(areaId).info);
		}
		
		if (All7d.getAll7d().get7d(areaId)!=null){
			result.put("7d", All7d.getAll7d().get7d(areaId).aggregateData);
		}
		return result;
		
	}
}
