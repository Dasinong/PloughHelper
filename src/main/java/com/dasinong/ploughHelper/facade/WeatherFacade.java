package com.dasinong.ploughHelper.facade;

import java.util.HashMap;

import com.dasinong.ploughHelper.ruleEngine.rules.Rule;
import com.dasinong.ploughHelper.weather.AllLocation;
import com.dasinong.ploughHelper.weather.All24h;
import com.dasinong.ploughHelper.weather.All7d;
import com.dasinong.ploughHelper.weather.ForcastDInfo;
import com.dasinong.ploughHelper.weather.GetLiveWeather;

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
		result.put("current", glw.getLiveWeather());
		
		
		//获得12小时天气
		
		try {
			ForcastDInfo[] n24h;
			n24h = All24h.get24h().get24h(areaId).info;
			if (All24h.get24h().get24h(areaId)!=null){
				result.put("n12h", n24h);
			}
			int p1=0;
			int p2=0;
			int p3=0;
			int p4=0;
			
			//获得24小时降水概率
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
		} catch (Exception e) {
			System.out.println("Get next 24h failed");
			System.out.println(e.getMessage());
		}
		
        //获得7天预测
		try {
			if (All7d.getAll7d().get7d(areaId)!=null){
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
