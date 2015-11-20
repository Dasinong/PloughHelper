package com.dasinong.ploughHelper.facade;

import java.util.Date;
import java.util.HashMap;

import com.dasinong.ploughHelper.datapool.AllMonitorLocation;
import com.dasinong.ploughHelper.ruleEngine.rules.Rule;
import com.dasinong.ploughHelper.util.LunarHelper;
import com.dasinong.ploughHelper.weather.All24h;
import com.dasinong.ploughHelper.weather.All7d;
import com.dasinong.ploughHelper.weather.ForcastDInfo;
import com.dasinong.ploughHelper.weather.GetLive24h;
import com.dasinong.ploughHelper.weather.GetLive7d;
import com.dasinong.ploughHelper.weather.GetLiveWeather;
import com.dasinong.ploughHelper.weather.Live7dFor;
import com.dasinong.ploughHelper.weather.LiveWeatherData;
import com.dasinong.ploughHelper.weather.SevenDayForcast.ForcastInfo;
import com.dasinong.ploughHelper.weather.TwentyFourHourForcast;

public class WeatherFacade implements IWeatherFacade {
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dasinong.ploughHelper.facade.IWeatherFacade#getWeather(double,
	 * double)
	 */
	@Override
	public Object getWeather(double lat, double lon) {
		try {
			Long mlId = AllMonitorLocation.getInstance().getNearest(lat, lon);
			return getWeather(mlId);
		} catch (Exception e) {
			HashMap<String, Object> result = new HashMap<String, Object>();
			result.put("respCode", 405);
			result.put("message", "初始化检测地址列表出错");
			return result;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dasinong.ploughHelper.facade.IWeatherFacade#getWeather(java.lang.
	 * Integer)
	 */
	@Override
	public Object getWeather(Long areaId) {

		HashMap<String, Object> result = new HashMap<String, Object>();
		HashMap<String, Object> data = new HashMap<String, Object>();

		// TODO (xiahonggao): fix respcode typo
		result.put("respcode", 200);
		result.put("respCode", 200);
		result.put("message", "获取成功");

		// 获得当前天气
		GetLiveWeather glw = new GetLiveWeather(areaId.toString());
		LiveWeatherData lwd = glw.getLiveWeather();
		result.put("current", lwd);
		data.put("current", lwd);

		// 获得12小时天气
		TwentyFourHourForcast tfhf;
		GetLive24h g24 = new GetLive24h(areaId.toString());
		tfhf = g24.getLiveWeather();
		if (tfhf == null)
			tfhf = All24h.get24h().get24h(areaId);
		if (tfhf != null) {
			try {
				// ForcastDInfo[] n24h = tfhf.info;
				ForcastDInfo[] n24h = new ForcastDInfo[25];
				int count = 0;
				Date cur = new Date();
				int max = -100;
				int min = 50;
				for (ForcastDInfo f : tfhf.info) {
					if ((f != null) && f.time.after(cur)) {
						n24h[count] = f;
						count++;
					}
					if (f != null) {
						max = Math.max(f.temperature, max);
						min = Math.min(f.temperature, min);
					}
				}
				;
				lwd.daymax = Math.max(max, lwd.l1);
				lwd.daymin = Math.min(min, lwd.l1);
				result.put("n12h", n24h);
				data.put("n24h", n24h);

				int p1 = 0;
				int p2 = 0;
				int p3 = 0;
				int p4 = 0;

				// 获得24小时降水概率
				if (tfhf.getSize() <= 24) {
					System.out.println("Not enough data to compute POP on area " + areaId);
				} else {
					for (int i = 0; i < tfhf.getSize(); i++) {
						if (tfhf.info[i].time.getHours() < 6)
							p1 = Math.max(p1, tfhf.info[i].pOP);
						else if (tfhf.info[i].time.getHours() < 12)
							p2 = Math.max(p2, tfhf.info[i].pOP);
						else if (tfhf.info[i].time.getHours() < 18)
							p3 = Math.max(p3, tfhf.info[i].pOP);
						else if (tfhf.info[i].time.getHours() < 24)
							p4 = Math.max(p4, tfhf.info[i].pOP);
					}

					HashMap<String, Integer> pOP = new HashMap<String, Integer>();
					pOP.put("morning", p1);
					pOP.put("noon", p2);
					pOP.put("night", p3);
					pOP.put("nextmidnight", p4);
					result.put("POP", pOP);
					data.put("POP", pOP);
				}
			} catch (Exception e) {
				System.out.println("Process 24h failed " + areaId);
				System.out.println(e.getMessage());
			}
		} else {
			System.out.println("Get next 24h failed " + areaId);
		}

		// 获得7天预测
		try {
			if (All7d.getAll7d().get7d(areaId) != null) {
				// Fix the missing last day data;
				int i = 0;
				ForcastInfo lastDay = null;
				for (ForcastInfo f : All7d.getAll7d().get7d(areaId).aggregateData) {
					if (f != null) {
						i++;
						lastDay = f;
					}
				}

				try {
					Live7dFor l7d = GetLive7d.getAllLive7d().getLive7d(areaId);
					Long sunrise = l7d.sevenDay[0].sunrise.getTime();
					if (System.currentTimeMillis() > sunrise)
						sunrise = sunrise + 24 * 60 * 60 * 1000;
					Long sunset = l7d.sevenDay[0].sunset.getTime();
					if (System.currentTimeMillis() > sunset)
						sunset = sunset + 24 * 60 * 60 * 1000;
					result.put("sunrise", sunrise);
					result.put("sunset", sunset);
					data.put("sunrise", sunrise);
					data.put("sunset", sunset);
					if (lastDay != null) {
						lastDay.max_temp = l7d.sevenDay[i - 1].dayTemp;
						lastDay.min_temp = l7d.sevenDay[i - 1].nightTemp;
					}
				} catch (Exception e) {
					System.out.println("Not able to load normal 7d");
					e.printStackTrace();
				}

				ForcastInfo[] n7d = All7d.getAll7d().get7d(areaId).aggregateData;
				result.put("n7d", n7d);
				data.put("n7d", n7d);
			}
		} catch (Exception e) {
			System.out.println("Get next 7d failed");
			System.out.println(e.getMessage());
		}

		try {
			int workable = Rule.workable(areaId);
			int sprayable = Rule.sprayable(areaId);
			result.put("workable", workable);
			result.put("sprayable", sprayable);
			data.put("workable", workable);
			data.put("sprayable", sprayable);
		} catch (Exception e) {
			System.out.println("No detailed 24h statistic for location " + areaId);
			result.put("workable", -1);
			result.put("sprayable", -1);
			data.put("workable", -1);
			data.put("sprayable", -1);
		}

		data.put("date", LunarHelper.getTodayLunar());

		result.put("data", data);
		return result;

	}
}
