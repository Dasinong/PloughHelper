package com.dasinong.ploughHelper.facade;

import java.util.Date;
import java.util.HashMap;

import com.dasinong.ploughHelper.ruleEngine.rules.Rule;
import com.dasinong.ploughHelper.weather.AllLocation;
import com.dasinong.ploughHelper.weather.All24h;
import com.dasinong.ploughHelper.weather.All7d;
import com.dasinong.ploughHelper.weather.ForcastDInfo;
import com.dasinong.ploughHelper.weather.GetLiveWeather;
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
		result.put("current", glw.getLiveWeather());
		
		
		//获得12小时天气
		TwentyFourHourForcast tfhf = All24h.get24h().get24h(areaId);
		if (tfhf!=null){
			try {
				//ForcastDInfo[] n24h = tfhf.info;
				ForcastDInfo[] n24h = new ForcastDInfo[25];
				int count=0;
				Date cur = new Date();
				for(ForcastDInfo f: tfhf.info){
					if ((f!=null) && f.time.after(cur)){
						n24h[count] = f;
						count++;
					}
				};
				
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
