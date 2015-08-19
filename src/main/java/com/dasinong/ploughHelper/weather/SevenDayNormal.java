package com.dasinong.ploughHelper.weather;

import java.util.Date;

import org.codehaus.jackson.JsonNode;

public class SevenDayNormal {
	String dayWeather;
	String nightWeather;
	int dayTemp;
	int nightTemp;
	short dayWD;
	short nightWD;
	short dayWP;
	short nightWP;
	String morning;
	String night;
	
	public SevenDayNormal(String dayWeather, String nightWeather, int dayTemp, int nightTemp, short dayWD, short nightWD,
			short dayWP, short nightWP, String morning, String night) {
		super();
		this.dayWeather = dayWeather;
		this.nightWeather = nightWeather;
		this.dayTemp = dayTemp;
		this.nightTemp = nightTemp;
		this.dayWD = dayWD;
		this.nightWD = nightWD;
		this.dayWP = dayWP;
		this.nightWP = nightWP;
		this.morning = morning;
		this.night = night;
	}

	public SevenDayNormal(JsonNode forcast) {
		this.dayWeather = forcast.get("fa").getTextValue();
		this.nightWeather = forcast.get("fb").getTextValue();
		try{
			this.dayTemp = Integer.parseInt(forcast.get("fc").getTextValue());
		}catch(Exception e){
			this.dayTemp = -100;
		}
		this.nightTemp = forcast.get("fd").getIntValue();
		try{
			this.dayWD = Short.parseShort(forcast.get("fe").getTextValue());
		}
		catch(Exception e){
			this.dayWD = -1;
		}
		try{
			this.nightWD = Short.parseShort(forcast.get("ff").getTextValue());
		}
		catch(Exception e){
			this.nightWD = -1;
		}
		try{
			this.dayWP = Short.parseShort(forcast.get("fg").getTextValue());
		}catch(Exception e){
			this.dayWP = -1;
		}
		try{
			this.nightWP = Short.parseShort(forcast.get("fh").getTextValue());
		}catch(Exception e){
			this.nightWP = -1;
		}
		String[] daynight = forcast.get("fi").getTextValue().split("|");
		if (daynight.length==2){
			this.morning = daynight[0];
			this.night = daynight[1];
		}
	}
}
