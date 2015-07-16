package com.dasinong.ploughHelper.ruleEngine.rules;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.dasinong.ploughHelper.weather.All24h;
import com.dasinong.ploughHelper.weather.TwentyFourHourForcast;

//This class is to define rules with direct data in hand.
public class Rule {
	public static boolean workable(int monitorLocationId){
		boolean workable=true;
		TwentyFourHourForcast tfhf = All24h.get24h().get24h(monitorLocationId);
		if (tfhf == null){
			System.out.println("获取"+monitorLocationId+"地区24小时预测失败");
		}
		
		return workable;
	}
	
	public static boolean sprayable(int monitorLocationId){
		boolean sprayable=true;
		TwentyFourHourForcast tfhf = All24h.get24h().get24h(monitorLocationId);
		if (tfhf == null){
			System.out.println("获取"+monitorLocationId+"地区24小时预测失败");
		}
		int max = 0;
		int min= 35;
		for (int i=0;i<tfhf.info.length;i++){
			if (tfhf.info[i].temperature > max){
				max = tfhf.info[i].temperature;
			}
			if (tfhf.info[i].temperature < min){
				min = tfhf.info[i].temperature;
			}
		}
		if (max>=35 || min<=12) sprayable =false;
		/*
		int i =0;
		int count =0;
		Date data = new Date();

		while (i<tfhf.info.length && count<6){
			if (tfhf.info[i].time.getTime()> data.getTime()){
				count++;
				if (tfhf.info[i].windSpeed_10m>3) sprayable =false;
			}
			i++;
		}
		if (count<6) sprayable = false;
		*/
		for (int i=0;i<tfhf.info.length;i++){
			if (tfhf.info[i].windSpeed_10m>3) sprayable =false;
			if (tfhf.info[i].icon.equals("clear") || tfhf.info[i].icon.equals("clearnight") || tfhf.info[i].icon.equals("cloudy")||
				tfhf.info[i].icon.equals("cloudynight") || tfhf.info[i].icon.equals("mostlyclear") || tfhf.info[i].icon.equals("mostlyclearnight")||
				tfhf.info[i].icon.equals("mostlycloudy") || tfhf.info[i].icon.equals("mostlycloudynight") ||
				tfhf.info[i].icon.equals("partlycloudy") || tfhf.info[i].icon.equals("partlycloudynight")){}
			else{
				sprayable = false;
			}
		}
		
		return sprayable;
	}

	
	
}
