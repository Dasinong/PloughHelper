package com.dasinong.ploughHelper.laonong;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dasinong.ploughHelper.model.LaoNongType;
import com.dasinong.ploughHelper.model.User;
import com.dasinong.ploughHelper.weather.AgriDisForcast;
import com.dasinong.ploughHelper.weather.AllAgriDisForcast;
import com.dasinong.ploughHelper.weather.GetWeatherAlert;
import com.dasinong.ploughHelper.weather.WeatherAlert;

public class WeatherAlertDataSource implements ILaoNongDataSource {
	
	private Logger logger = LoggerFactory.getLogger(WeatherAlertDataSource.class);

	@Override
	public List<LaoNong> genLaoNongs(User user, Long areaId) {
		GetWeatherAlert gwa = new GetWeatherAlert(areaId.toString());
		List<WeatherAlert> wa = gwa.getWeatherAlert();
		String disasterInfo = "";
		String agriURLTag = "";
		List<LaoNong> laonongs = new ArrayList<LaoNong>();
		try {
			AgriDisForcast agri = AllAgriDisForcast.getadf().getadf(areaId);
			if (agri != null)
				disasterInfo = agri.getDisasterInfo();
		} catch (Exception e) {
			this.logger.error("Error happend when get Agriculture Disaster Forcast", e);
		}

		System.out.println("szc: getLaoNong areaId : " + areaId);
		LaoNong laoNong = null;

		if (!"".equals(disasterInfo)) {
			laoNong = new LaoNong(0L, LaoNongType.AGRICULTURE_WEATHER_ALERT, "ohnoface.png", "农业预警", disasterInfo,
					agriURLTag);
			laonongs.add(laoNong);
		}
		if (wa != null) {
			for (WeatherAlert w : wa) {
				laoNong = new LaoNong(0L, LaoNongType.AGRICULTURE_WEATHER_ALERT, "ohnoface.png", "天气预警",
						w.shortDescription(), w.urlTag());
				laonongs.add(laoNong);
			}
		}
		
		return laonongs;
	}

}
