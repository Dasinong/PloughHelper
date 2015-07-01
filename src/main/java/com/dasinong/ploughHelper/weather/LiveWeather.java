package com.dasinong.ploughHelper.weather;

import org.json.JSONObject;



public class LiveWeather {
	//{"observe":{"101010100":{"l":{"l1":"22","l2":"76","l3":"1","l4":"8","l5":"04","l6":"0","l6":"0.0","l7":"00:10"}}}}
	
	int code;
	int l1;
	int l2;
	int l3;
	String l4;
	int l5;
	double l6;
	String l7;
	
	class Wrapper1{
		Wrapper2 a;
	}
	class Wrapper2{
		LiveWeather a;
	}
	
	//{"observe":{"101010100":{"l":{"l1":"22","l2":"76","l3":"1","l4":"8","l5":"04","l6":"0","l6":"0.0","l7":"00:10"}}}}
	//{"observe":{"101010100":{"l":{"l1":"22","l2":"78","l3":"2","l4":"1","l5":"04","l6":"0","l6":"0.0","l7":"00:45"}}}}
	
	public LiveWeather(String input){
		JSONObject j = new JSONObject(input); 
		
		//JSONObject j = new JSONObject(input);
		JSONObject j2 = j.getJSONObject("observe");

	
	}
	
	public static void main(String args[]){
		//String input = "{\"observe\":{\"101010100\":{\"l\":{\"l1\":\"22\",\"l2\":\"76\",\"l3\":\"1\",\"l4\":\"8\",\"l5\":\"04\",\"l6\":\"0\",\"l6\":\"0.0\",\"l7\":\"00:10\"}}}}";
		String input = "{\"a\":\"13\",\"b\":\"test\"}";
		LiveWeather liveweather = new LiveWeather(input);
	}

}
