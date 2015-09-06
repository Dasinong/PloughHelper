package com.dasinong.ploughHelper.facade;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;

import com.dasinong.ploughHelper.ruleEngine.rules.Rule;
import com.dasinong.ploughHelper.weather.AllLocation;
import com.dasinong.ploughHelper.weather.All24h;
import com.dasinong.ploughHelper.weather.All7d;
import com.dasinong.ploughHelper.weather.AllLive7d;
import com.dasinong.ploughHelper.weather.ForcastDInfo;
import com.dasinong.ploughHelper.weather.GetLive24h;
import com.dasinong.ploughHelper.weather.GetLiveWeather;
import com.dasinong.ploughHelper.weather.Live7dFor;
import com.dasinong.ploughHelper.weather.LiveWeatherData;
import com.dasinong.ploughHelper.weather.SevenDayForcast.ForcastInfo;
import com.dasinong.ploughHelper.weather.TwentyFourHourForcast;

public class WeatherFacade implements IWeatherFacade {
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IWeatherFacade#getWeather(double, double)
	 */
	@Override
	public Object getWeather(double lat, double lon){
		try{
			Integer mlId = AllLocation.getLocation().getNearest(lat, lon);
			return getWeather(mlId);
		}
		catch(Exception e){
			HashMap<String,Object> result = new HashMap<String,Object>();
			result.put("respCode", 405);
			result.put("message", "初始化检测地址列表出错");
			return result;
		}
	}
	/* (non-Javadoc)
	 * @see com.dasinong.ploughHelper.facade.IWeatherFacade#getWeather(java.lang.Integer)
	 */
	@Override
	public Object getWeather(Integer areaId){
		
		HashMap<String,Object> result = new HashMap<String,Object>();
		result.put("respcode", 200);
		result.put("message", "获取成功");
		
		//获得当前天气
		GetLiveWeather glw = new GetLiveWeather(areaId.toString());
		LiveWeatherData lwd = glw.getLiveWeather();
		result.put("current", lwd);
		
		
		//获得12小时天气
		TwentyFourHourForcast tfhf;
		GetLive24h g24 = new GetLive24h(areaId.toString());
		tfhf = g24.getLiveWeather();
		if (tfhf==null) tfhf = All24h.get24h().get24h(areaId);
		if (tfhf!=null){
			try {
				//ForcastDInfo[] n24h = tfhf.info;
				ForcastDInfo[] n24h = new ForcastDInfo[25];
				int count=0;
				Date cur = new Date();
				int max=-100;
				int min=50;
				for(ForcastDInfo f: tfhf.info){
					if ((f!=null) && f.time.after(cur)){
						n24h[count] = f;
						count++;
					}
					if (f!=null){
						max = Math.max(f.temperature, max);
						min = Math.min(f.temperature, min);
					}
				};
				lwd.daymax = Math.max(max, lwd.l1);
				lwd.daymin = Math.min(min, lwd.l1);
				result.put("n12h", n24h);

				int p1=0;
				int p2=0;
				int p3=0;
				int p4=0;
				
				//获得24小时降水概率
				if (tfhf.getSize()<=24)
				{
					System.out.println("Not enough data to compute POP on area "+ areaId);
				}
				else{
			        for(int i=0;i<tfhf.getSize();i++){
			    	   if (tfhf.info[i].time.getHours()<6) p1=Math.max(p1, tfhf.info[i].pOP);
			    	   else if (tfhf.info[i].time.getHours()<12) p2=Math.max(p2, tfhf.info[i].pOP);
			    	   else if (tfhf.info[i].time.getHours()<18) p3=Math.max(p3, tfhf.info[i].pOP);
			    	   else if (tfhf.info[i].time.getHours()<24) p4=Math.max(p4, tfhf.info[i].pOP);
			        }
			        
			        HashMap<String,Integer> pOP = new HashMap<String,Integer>();
			        pOP.put("morning", p1);
				    pOP.put("noon", p2);
				    pOP.put("night", p3);
				    pOP.put("nextmidnight", p4);
			        result.put("POP", pOP);
				}
			} catch (Exception e) {
				System.out.println("Process 24h failed "+areaId);
				System.out.println(e.getMessage());
			}
		}
		else{
			System.out.println("Get next 24h failed "+ areaId);
		}
		
        //获得7天预测
		try {
			if (All7d.getAll7d().get7d(areaId)!=null){
				//Fix the missing last day data;
				int i=0;
				ForcastInfo lastDay=null;
				for(ForcastInfo f: All7d.getAll7d().get7d(areaId).aggregateData){
					if (f!=null){
						i++;
						lastDay=f;
					}
				}
				
				try {
					Live7dFor l7d = AllLive7d.getAllLive7d().getLive7d(areaId);
					Long sunrise = l7d.sevenDay[0].sunrise.getTime();
					Long sunset = AllLive7d.getAllLive7d().getLive7d(areaId).sevenDay[0].sunset.getTime();
					result.put("sunrise", sunrise);
					result.put("sunset", sunset);
					if (lastDay!=null){
						lastDay.max_temp = l7d.sevenDay[i-1].dayTemp;
						lastDay.min_temp = l7d.sevenDay[i-1].nightTemp;
					}
				} catch (Exception e) {
					System.out.println("Not able to load normal 7d");
					e.printStackTrace();
				}
				
				result.put("n7d", All7d.getAll7d().get7d(areaId).aggregateData);
			}
		} catch (Exception e) {
			System.out.println("Get next 7d failed");
			System.out.println(e.getMessage());
		}
		
		
		
		try{
			result.put("workable", Rule.workable(areaId));
			result.put("sprayable", Rule.sprayable(areaId));
		}
		catch(Exception e){
			System.out.println("No detailed 24h statistic for location "+ areaId);
			result.put("workable", -1);
			result.put("sprayable", -1);
		}
		return result;
		
	}
}
