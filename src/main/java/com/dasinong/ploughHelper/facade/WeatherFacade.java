package com.dasinong.ploughHelper.facade;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

import com.dasinong.ploughHelper.weather.All24h;
import com.dasinong.ploughHelper.weather.All7d;
import com.dasinong.ploughHelper.weather.ForcastDInfo;
import com.dasinong.ploughHelper.weather.GetLiveWeather;

public class WeatherFacade {
	public Object getWeather(Integer areaId) throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException{
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put("respcode", 200);
		result.put("message", "获取成功");
		
		GetLiveWeather glw = new GetLiveWeather(areaId.toString());
		result.put("current", glw.getLiveWeather());
		
		
		ForcastDInfo[]  n24h = All24h.get24h().get24h(areaId).info;
		if (All24h.get24h().get24h(areaId)!=null){
			result.put("n12h", n24h);
		}
		
		int p1=0;
		int p2=0;
		int p3=0;
		int p4=0;
		
        for(int i =0; i<6; i++){
        	if (n24h[i]!=null && n24h[i].pOP>p1) p1=n24h[i].pOP;
        }
        for(int i =6; i<12; i++){
        	if (n24h[i]!=null && n24h[i].pOP>p2) p2=n24h[i].pOP;
        }
        for(int i =12; i<18; i++){
        	if (n24h[i]!=null && n24h[i].pOP>p3) p3=n24h[i].pOP;
        }
        for(int i =18; i<24; i++){
        	if (n24h[i]!=null && n24h[i].pOP>p4) p4=n24h[i].pOP;
        }
        
        HashMap<String,Integer> pOP = new HashMap<String,Integer>();
        pOP.put("morning", p1);
        pOP.put("noon", p2);
        pOP.put("night", p3);
        pOP.put("nextmidnight", p4);
        
        result.put("POP", pOP);
		
		if (All7d.getAll7d().get7d(areaId)!=null){
			result.put("n7d", All7d.getAll7d().get7d(areaId).aggregateData);
		}
		
		return result;
		
	}
}
