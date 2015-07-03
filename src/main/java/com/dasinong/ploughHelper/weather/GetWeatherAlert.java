package com.dasinong.ploughHelper.weather;

import com.dasinong.ploughHelper.modelTran.WeatherAlert;
import com.dasinong.ploughHelper.util.WISWeather;



public class GetWeatherAlert {
	
	private String areaId;
	
	public GetWeatherAlert(String areaId){
		this.areaId=areaId;
	}
	
	public WeatherAlert getWeatherAlert() {
		WISWeather wisw = new WISWeather(this.areaId,"alarm");
		String result = wisw.Commute();
		return null;
	}
	
	public static void main(String[] args){
		GetWeatherAlert gwa = new GetWeatherAlert("101010100");
		gwa.getWeatherAlert();
	}
}
