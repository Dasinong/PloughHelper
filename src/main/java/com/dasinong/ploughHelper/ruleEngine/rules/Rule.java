package com.dasinong.ploughHelper.ruleEngine.rules;

import java.io.IOException;
import java.text.ParseException;

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

}
