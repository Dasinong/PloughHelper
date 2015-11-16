package com.dasinong.ploughHelper.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.dasinong.ploughHelper.datapool.AllMonitorLocation;
import com.dasinong.ploughHelper.datapool.AllSystemMessage;
import com.dasinong.ploughHelper.model.LaoNongType;
import com.dasinong.ploughHelper.model.Proverb;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.model.nohibernate.SystemMessage;
import com.dasinong.ploughHelper.modelTran.LaoNong;
import com.dasinong.ploughHelper.modelTran.NongYan;
import com.dasinong.ploughHelper.modelTran.WeatherAlert;
import com.dasinong.ploughHelper.weather.GetWeatherAlert;
import com.dasinong.ploughHelper.weather.AgriDisForcast;
import com.dasinong.ploughHelper.weather.AllAgriDisForcast;

public class LaoNongFacade implements ILaoNongFacade {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dasinong.ploughHelper.facade.ILaoNongFacade#getLaoNong(double,
	 * double)
	 */
	@Override
	public Object getLaoNong(double lat, double lon, User user) {
		try {
			Integer mlId = AllMonitorLocation.getInstance().getNearest(lat, lon);
			return getLaoNong(mlId, user);
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
	 * com.dasinong.ploughHelper.facade.ILaoNongFacade#getLaoNong(java.lang.
	 * Integer)
	 */
	@Override
	public Object getLaoNong(Integer areaId, User user) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		GetWeatherAlert gwa = new GetWeatherAlert(areaId.toString());
		List<WeatherAlert> wa = gwa.getWeatherAlert();
		String DisasterInfo = "";
		String AgriURLTag = "";
		List<LaoNong> newLaoNong = new ArrayList<LaoNong>();
		try {
			AgriDisForcast agri = AllAgriDisForcast.getadf().getadf(areaId);
			if (agri != null)
				DisasterInfo = agri.getDisasterInfo();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error happend when get Agriculture Disaster Forcast");
		}

		System.out.println("szc: getLaoNong areaId : " + areaId);
		LaoNong laoNong = null;

		if (!"".equals(DisasterInfo)) {
			laoNong = new LaoNong(0, LaoNongType.AGRICULTURE_WEATHER_ALERT, "ohnoface.png", "农业预警", DisasterInfo, AgriURLTag);
			result.put("data", laoNong);
			newLaoNong.add(laoNong);
		}
		if (wa != null) {
			boolean first = true;
			for (WeatherAlert w : wa) {
				laoNong = new LaoNong(0, LaoNongType.AGRICULTURE_WEATHER_ALERT, "ohnoface.png", "天气预警", w.shortDescription(), w.urlTag());
				if (first) {
					result.put("data", laoNong);
					first = false;
				}
				newLaoNong.add(laoNong);
			}
		}
		List<SystemMessage> sml = new ArrayList<SystemMessage>();
		List<SystemMessage> local = AllSystemMessage.getSystemMessage().get_Messages(areaId);
		List<SystemMessage> all = AllSystemMessage.getSystemMessage().get_Messages(100);
		if (all != null) {
			sml.addAll(all);
		}
		if (local != null) {
			sml.addAll(local);
		}

		if (sml != null && user != null) {
			for (SystemMessage sm : sml) {
				if ((sm.getInstitutionId() == user.getInstitutionId() || sm.getInstitutionId() == 0)
						&& (sm.getStartTime().getTime() < (new Date()).getTime())) {
					LaoNong ln = new LaoNong(sm.getId(), LaoNongType.SYSTEM_MESSAGE, sm.getPicUrl(), "系统广告", sm.getContent(),
							sm.getLandingUrl());
					result.put("data", ln);
					newLaoNong.add(ln);
				}
			}
		}

		if (newLaoNong.size() == 0) {
			Proverb proverb = NongYan.allNongYan().getNongYan(areaId);
			laoNong = new LaoNong(1, LaoNongType.PROVERB, "closeeyelaugh.png", "每日农谚", proverb.getContent(), "");
			result.put("data", laoNong);
			newLaoNong.add(laoNong);
		}

		result.put("respCode", 200);
		result.put("message", "获得老农成功");
		result.put("newdata", newLaoNong);
		return result;
	}

	// TODO (xiahonggao): deprecate getLaoNong
	@Override
	public Object getLaoNongs(Integer areaId, User user) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		GetWeatherAlert gwa = new GetWeatherAlert(areaId.toString());
		List<WeatherAlert> wa = gwa.getWeatherAlert();
		String disasterInfo = "";
		String agriURLTag = "";
		List<LaoNong> laoNongs = new ArrayList<LaoNong>();
		try {
			AgriDisForcast agri = AllAgriDisForcast.getadf().getadf(areaId);
			if (agri != null)
				disasterInfo = agri.getDisasterInfo();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error happend when get Agriculture Disaster Forcast");
		}

		System.out.println("szc: getLaoNong areaId : " + areaId);
		LaoNong laoNong = null;

		if (!"".equals(disasterInfo)) {
			laoNong = new LaoNong(0, LaoNongType.AGRICULTURE_WEATHER_ALERT, "ohnoface.png", "农业预警", disasterInfo,
					agriURLTag);
			laoNongs.add(laoNong);
		}
		if (wa != null) {
			for (WeatherAlert w : wa) {
				laoNong = new LaoNong(0, LaoNongType.AGRICULTURE_WEATHER_ALERT, "ohnoface.png", "天气预警",
						w.shortDescription(), w.urlTag());
				laoNongs.add(laoNong);
			}
		}
		List<SystemMessage> sml = new ArrayList<SystemMessage>();
		List<SystemMessage> local = AllSystemMessage.getSystemMessage().get_Messages(areaId);
		List<SystemMessage> all = AllSystemMessage.getSystemMessage().get_Messages(100);
		if (all != null) {
			sml.addAll(all);
		}
		if (local != null) {
			sml.addAll(local);
		}

		if (sml != null && user != null) {
			for (SystemMessage sm : sml) {
				if ((sm.getInstitutionId() == user.getInstitutionId() || sm.getInstitutionId() == 0)
						&& (sm.getStartTime().getTime() < (new Date()).getTime())) {
					LaoNong ln = new LaoNong(sm.getId(), LaoNongType.SYSTEM_MESSAGE, sm.getPicUrl(), "系统广告", sm.getContent(),
							sm.getLandingUrl());
					laoNongs.add(ln);
				}
			}
		}

		if (laoNongs.size() == 0) {
			Proverb proverb = NongYan.allNongYan().getNongYan(areaId);
			laoNong = new LaoNong(1, LaoNongType.PROVERB, "closeeyelaugh.png", "每日农谚", proverb.getContent(), "");
			laoNongs.add(laoNong);
		}

		HashMap<String, Object> data = new HashMap<String, Object>();
		data.put("laonongs", laoNongs);
		result.put("respCode", 200);
		result.put("message", "获得老农成功");
		result.put("data", data);
		return result;
	}

	@Override
	public Object getLaoNongs(double lat, double lon, User user) {
		Integer mlId = AllMonitorLocation.getInstance().getNearest(lat, lon);
		return getLaoNongs(mlId, user);
	}
}
