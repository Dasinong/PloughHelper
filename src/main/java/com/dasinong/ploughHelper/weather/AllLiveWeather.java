package com.dasinong.ploughHelper.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class AllLiveWeather {
	private static AllLiveWeather allLiveWeather;
	
	public static AllLiveWeather getAllLiveWeather() throws IOException, ParseException, InterruptedException{
		if (allLiveWeather==null){
			allLiveWeather = new AllLiveWeather();
			return allLiveWeather;
		}
		else{
			return allLiveWeather;
		}
		
	}
	private AllLiveWeather() throws IOException, ParseException, InterruptedException{
		_allLiveWeather = new HashMap<Integer,LiveWeatherData>();
		loadContent();
	}
    public void updateContent() throws IOException, ParseException{
     	HashMap<Integer,LiveWeatherData> oldLiveWeather = _allLiveWeather;
    	_allLiveWeather = new HashMap<Integer,LiveWeatherData>();
		try{
			loadContent();
		}
		catch(Exception e){
			System.out.println("update LiveWeather failed. " +  e.getCause());
			_allLiveWeather = oldLiveWeather;
		}
    }
	
	private void loadContent() throws IOException, ParseException, InterruptedException {
		Set<Integer> locations  = AllLocation.getLocation()._allLocation.keySet();
		//Clone a copy of the keys;

		Set<Integer> tocheck = new HashSet<Integer>();
		for (Integer code : locations){
			tocheck.add(new Integer(code));
		}
		GetLiveWeather glw = new GetLiveWeather();
		for (int i=0;i<3;i++){
			for(Integer code : tocheck){
				glw.setAreaId(code.toString());
				LiveWeatherData result = glw.getLiveWeather();
				
			}
			Thread.sleep(1000);
		}
		
	}
	private HashMap<Integer,LiveWeatherData> _allLiveWeather;
	
	public LiveWeatherData getLiveWeather(Integer aid){
		
		return _allLiveWeather.get(aid);
	}
	

	
	public static void main(String[] args) throws IOException, ParseException{
		/*
		Iterator iter= AllLiveWeather.getAllLiveWeather()._allLiveWeather.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(entry.getValue());
		}
		AllLiveWeather.getAllLiveWeather().getLiveWeather(101090301);
		*/
	}
}
