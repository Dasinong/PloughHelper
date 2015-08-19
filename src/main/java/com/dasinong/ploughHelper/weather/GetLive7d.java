package com.dasinong.ploughHelper.weather;

import java.util.HashMap;

import com.dasinong.ploughHelper.util.Env;
import com.dasinong.ploughHelper.util.WISWeather;

public class GetLive7d {
	
	private String areaId;
	
	public GetLive7d(){
	}
	public GetLive7d(String areaId){
		this.areaId=areaId;
	}
	
	public Live7dFor getLive7d(){
        long currentTime = System.currentTimeMillis();
        //如果上次关于这个地方的天气请求距离这次不到live7dBufferTime分钟，那么直接返回缓存的天气数据
        if (GetLive7d._Live7dFor.containsKey(this.areaId) && (currentTime - GetLive7d._Live7dFor.get(this.areaId).timeStamp.getTime())< Env.getEnv().live7dBufferTime )
        	return GetLive7d._Live7dFor.get(this.areaId);
        
        WISWeather wisw = new WISWeather(this.areaId,"forecast7d");
		String result = wisw.Commute();
		
		Live7dFor live7dFor = null;
		try{
			live7dFor = new Live7dFor(result,Integer.parseInt(this.areaId));
			 _Live7dFor.put(this.areaId, live7dFor);
	    } catch (Exception e) {
			 System.out.println("Error happend when processing 7d live forcast!");
			 System.out.println(result);
			 GetLive7d._Live7dFor.get(this.areaId);
	    }	
		return live7dFor;
	}
	
	private static HashMap<String,Live7dFor> _Live7dFor = new HashMap<String,Live7dFor>();
		
	public static void main (String args[]){	
		GetLive7d gh = new GetLive7d("101010100");
		gh.getLive7d();		
	}
	public void setAreaId(String areaId) {
		this.areaId=areaId;
	}

}
