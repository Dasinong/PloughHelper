package com.dasinong.ploughHelper;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import org.springframework.web.servlet.ModelAndView;  
import org.springframework.web.servlet.mvc.Controller;  

import com.dasinong.ploughHelper.modelTran.LaoNong;
import com.dasinong.ploughHelper.modelTran.WeatherAlert;
import com.dasinong.ploughHelper.weather.GetWeatherAlert;
public class WeatherAlertController implements Controller {  
    @Override  
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {  
		//1、收集参数、验证参数  
		//2、绑定参数到命令对象  
		//3、将命令对象传入业务对象进行业务处理  
		//4、选择下一个页面  
    	ModelAndView mv = new ModelAndView();
    	try{
			String areaId = request.getParameter("areaId");
			GetWeatherAlert gwa = new GetWeatherAlert(areaId); 
			WeatherAlert wa = new WeatherAlert(areaId); 
			wa = gwa.getWeatherAlert();
			if (wa != null){
				//添加模型数据 可以是任意的POJO对象  
				mv.addObject("AlertTitle", wa.typeName + wa.levelName + "预警");
				mv.addObject("AlertContent", wa.content);
				mv.addObject("AlertTimeDate", wa.time.split(" ")[0]);
				mv.addObject("AlertTimeClock", wa.time.split(" ")[1]);
				//	设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面  
				mv.setViewName("WeatherAlert");
			} else {
				//添加模型数据 可以是任意的POJO对象  
				mv.addObject("AlertTitle", "台风红色预警");
				mv.addObject("AlertContent", "黔西南州气象台7月3日4时36分发布暴雨红色预警信号：目前我州兴义、册亨部分乡镇已出现大暴雨天气，预计未来3小时，我州大部有大到暴雨天气，兴义、安龙、册亨、望谟部分乡镇有大暴雨天气，请加强防范。黔西南州气象台7月3日4时36分发布暴雨红色预警信号：目前我州兴义、册亨部分乡镇已出现大暴雨天气，预计未来3小时，我州大部有大到暴雨天气，兴义、安龙、册亨、望谟部分乡镇有大暴雨天气，请加强防范。黔西南州气象台7月3日4时36分发布暴雨红色预警信号：目前我州兴义、册亨部分乡镇已出现大暴雨天气，预计未来3小时，我州大部有大到暴雨天气，兴义、安龙、册亨、望谟部分乡镇有大暴雨天气，请加强防范。黔西南州气象台7月3日4时36分发布暴雨红色预警信号：目前我州兴义、册亨部分乡镇已出现大暴雨天气，预计未来3小时，我州大部有大到暴雨天气，兴义、安龙、册亨、望谟部分乡镇有大暴雨天气，请加强防范。黔西南州气象台7月3日4时36分发布暴雨红色预警信号：目前我州兴义、册亨部分乡镇已出现大暴雨天气，预计未来3小时，我州大部有大到暴雨天气，兴义、安龙、册亨、望谟部分乡镇有大暴雨天气，请加强防范。黔西南州气象台7月3日4时36分发布暴雨红色预警信号：目前我州兴义、册亨部分乡镇已出现大暴雨天气，预计未来3小时，我州大部有大到暴雨天气，兴义、安龙、册亨、望谟部分乡镇有大暴雨天气，请加强防范。黔西南州气象台7月3日4时36分发布暴雨红色预警信号：目前我州兴义、册亨部分乡镇已出现大暴雨天气，预计未来3小时，我州大部有大到暴雨天气，兴义、安龙、册亨、望谟部分乡镇有大暴雨天气，请加强防范。黔西南州气象台7月3日4时36分发布暴雨红色预警信号：目前我州兴义、册亨部分乡镇已出现大暴雨天气，预计未来3小时，我州大部有大到暴雨天气，兴义、安龙、册亨、望谟部分乡镇有大暴雨天气，请加强防范。");
				mv.addObject("AlertTimeDate", "2015-07-03 04:36".split(" ")[0]);
				mv.addObject("AlertTimeClock", "2015-07-03 04:36".split(" ")[1]);
				//	设置逻辑视图名，视图解析器会根据该名字解析到具体的视图页面  
				mv.setViewName("WeatherAlert");
			}
	    }catch(Exception e){
    		e.printStackTrace();
    	}
       return mv;  
    }  
}  