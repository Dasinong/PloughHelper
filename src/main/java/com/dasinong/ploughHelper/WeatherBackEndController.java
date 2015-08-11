package com.dasinong.ploughHelper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dasinong.ploughHelper.weather.All24h;
import com.dasinong.ploughHelper.weather.All7d;
import com.dasinong.ploughHelper.weather.All7dHum;
import com.dasinong.ploughHelper.weather.AllAgriDisForcast;
import com.dasinong.ploughHelper.weather.AllCurrentJiwen;
import com.dasinong.ploughHelper.weather.IWeatherBuffer;
import com.dasinong.ploughHelper.weather.SoilLiquid;


@Controller
public class WeatherBackEndController {
	
	@RequestMapping(value = "/weatherBuffer", produces="application/json")
	@ResponseBody
	public Object weatherBuffer(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		String target = request.getParameter("target");
		String action = request.getParameter("action");
		if (target==null || target.equals("") || action==null || action.equals("")){
			result.put("respCode",300);
			result.put("message","参数错误，请输入target及action");
			return result;
		}
		IWeatherBuffer weatherBuffer = null;
		if (target.equalsIgnoreCase("24h")){
			weatherBuffer = All24h.get24h();
		}
		else if(target.equalsIgnoreCase("7d")){
			weatherBuffer = All7d.getAll7d();
		}
		else if(target.equalsIgnoreCase("7dhum")){
			weatherBuffer = All7dHum.get7dHum();
		}else if(target.equalsIgnoreCase("agriDis")){
			weatherBuffer = AllAgriDisForcast.getadf();
		}else if(target.equalsIgnoreCase("jiwen")){
			weatherBuffer = AllCurrentJiwen.getCurJiwen();
		}else if(target.equalsIgnoreCase("soilliq")){
			weatherBuffer = SoilLiquid.getSoilLi();
		}

		if (weatherBuffer==null){
			result.put("respCode",318);
			result.put("message", "Target not allowed");
			return result;
		}
		
		if (action.equalsIgnoreCase("update")){
			String source = request.getParameter("source");
			if (source == null || source.equals("")){
				weatherBuffer.updateContent();
			}
			else {
				weatherBuffer.updateContent(source);
			}
			result.put("respCode",200);
			result.put("message", "update "+target + " called. Check log for detailed execution result.");
			return result;
		}else if (action.equalsIgnoreCase("checkLatestUpdate")){
			result.put("respCode", 200);
			result.put("message", weatherBuffer.latestUpdate());
			return result;
		}
			
		result.put("respCode",320);
		result.put("message", "Action not allowed");
		return result;
	}
	
	@RequestMapping(value = "/update24Weather", produces="application/json")
	@ResponseBody
	public Object update24Weather(HttpServletRequest request, HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		int monitorLocationId;
		try{
			monitorLocationId = Integer.parseInt(request.getParameter("monitorLocationId"));
		}
		catch(Exception e){
			result.put("respCode",300);
			result.put("message", "参数或内容错误");
			return result;
		}

		result.put("respCode",200);
		result.put("message", "执行更新");
		result.put("message", All24h.get24h().updateLocation(monitorLocationId));
		return result;
	}

}
