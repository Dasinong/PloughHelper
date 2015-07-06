package com.dasinong.ploughHelper.modelTran;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


import java.util.Iterator;
import java.util.List;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;



public class WeatherAlert  implements Comparable<WeatherAlert> {
	
	public String province;
    public String city;
    public String country;
    public int typeId;
    public String typeName;
    public int level;
    public String levelName;
    public String time;
    public String content;
    public String urlTag;
    public String areaId;
    public Date timeStamp;
	
	//Initialize from webapi
	public WeatherAlert(String areaId) {
		this.province = "";
		this.city = "";
		this.country = "";
		this.typeId = 0;
		this.typeName = "";
		this.level = 0;
		this.levelName = "";
		this.time = "";
		this.urlTag = "weatherAlert";
		this.content = "";
		this.areaId = areaId;
		this.timeStamp = new Date();
		this.timeStamp.setTime(100000);  //Time set on create routine not guarantee content is good.
		
	}
	
	public static WeatherAlert parseHTTPResult(String areaId, String result)
			 throws JsonParseException,JsonProcessingException,IOException {
	//areaId is monitorLocationId in interface	
		ObjectMapper mapper = new ObjectMapper();
		List<WeatherAlert> waList = new ArrayList<WeatherAlert>();
		
		try{
			System.out.println("szc:"+result);
			JsonNode node = mapper.readTree(result);
			JsonNode alarm = node.get("alarm");
			JsonNode areaAlarm = alarm.get(areaId);
			JsonNode warnings = areaAlarm.get("w");
			System.out.println("areaId:"+areaId);
			if (warnings == null)
				return null;
			Iterator<JsonNode> warningNodes = warnings.getElements();
			while(warningNodes.hasNext()){
				WeatherAlert wa = new WeatherAlert("0");
				JsonNode w = warningNodes.next();
				wa.province = w.get("w1").toString().replace('\"', ' ').trim();
				wa.city = w.get("w2").toString().replace('\"', ' ').trim();
				wa.country = w.get("w3").toString().replace('\"', ' ').trim();
				wa.typeId = Integer.parseInt(w.get("w4").toString().replace('\"', ' ').trim());
				wa.typeName = w.get("w5").toString().replace('\"', ' ').trim();
				wa.level = Integer.parseInt(w.get("w6").toString().replace('\"', ' ').trim());
				wa.levelName = w.get("w7").toString().replace('\"', ' ').trim();
				wa.time = w.get("w8").toString().replace('\"', ' ').trim();
				wa.content = w.get("w9").toString().replace('\"', ' ').trim();
				wa.urlTag = "/weatherAlert?monitorLocationId="+areaId;
				wa.timeStamp = new Date();
				wa.areaId = areaId;
				waList.add(wa);
			}

			Collections.sort(waList);
			
		} catch (Exception e){
			System.out.println("Error happened when processing json live weather data!");
		    e.printStackTrace();
		}
		if (waList.size() > 0)
			// TODO 默认是升序，所以排序后返回第一个元素，是预警编号最小的值，可能是最严重的灾害
			return waList.get(0);
		else
			return null;
	}
	public String shortDescription(){
		String des = "";
		des = "气象台 " + this.time + "消息 ：" + this.province + this.city + this.country + "将有" + this.typeName + this.levelName + "预警。请防范。";
		return des;		
	}
	
	public String fullDescription(){
		return this.content;
	}
	
	public String urlTag(){
		return this.urlTag;
	}
	@Override
	public int compareTo(WeatherAlert o) {
		return o.level - this.level;
	}
}
