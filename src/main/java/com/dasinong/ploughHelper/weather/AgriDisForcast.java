package com.dasinong.ploughHelper.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import com.dasinong.ploughHelper.util.Env;

public class AgriDisForcast {
	String date = "";
	String avgTemp = ""; //平均气温
	String maxTemp = "";
	String minTemp = "";
	String windSpeed = "";
	String relativeHumidity = "";
	String disasterInfo = "";
	
	public AgriDisForcast(){}
	
	public AgriDisForcast(String date, String avgTemp, String maxTemp,
			String minTemp, String windSpeed, String relativeHumidity,
			String disasterInfo) {
		super();
		this.date = date;
		this.avgTemp = avgTemp;
		this.maxTemp = maxTemp;
		this.minTemp = minTemp;
		this.windSpeed = windSpeed;
		this.relativeHumidity = relativeHumidity;
		this.disasterInfo = disasterInfo;
	}
	
	public static HashMap<Integer,AgriDisForcast>	parseForcastFile(String filename) throws IOException{
		HashMap<Integer, AgriDisForcast> _alladf = new HashMap<Integer, AgriDisForcast>();
		File file = new File(filename);
		BufferedReader reader = null;
		try {
		    reader = new BufferedReader(new FileReader(file));
		    String tempString = null;
		    String [] fields;
		    while ((tempString = reader.readLine()) != null) {
		    	if(tempString.startsWith("areaid"))
		    		continue;
		    	fields = tempString.trim().split("\t");
		    	if(fields.length >= 7){
		    		AgriDisForcast adf = new AgriDisForcast();
		    		int areaId = Integer.parseInt(fields[0]);
		    		adf.setDate(fields[1]);
		    		adf.setAvgTemp(fields[2]);
		    		adf.setMaxTemp(fields[3]);
		    		adf.setMinTemp(fields[4]);
		    		adf.setWindSpeed(fields[5]);
		    		adf.setRelativeHumidity(fields[6]);
		    		if(fields.length == 8)
		    			adf.setDisasterInfo(fields[7]);
		    		else
		    			adf.setDisasterInfo("");
		    		_alladf.put(areaId, adf);
		    	} else {
		    		System.out.println("skip line :" + tempString);
		    	}
		    }
		    reader.close();
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    if (reader != null) {
		        try {
		            reader.close();
		        } catch (IOException e1) {
		        }
		    }
		}
		return _alladf;
	} 
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAvgTemp() {
		return avgTemp;
	}
	public void setAvgTemp(String avgTemp) {
		this.avgTemp = avgTemp;
	}
	public String getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(String maxTemp) {
		this.maxTemp = maxTemp;
	}
	public String getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(String minTemp) {
		this.minTemp = minTemp;
	}
	public String getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getRelativeHumidity() {
		return relativeHumidity;
	}
	public void setRelativeHumidity(String relativeHumidity) {
		this.relativeHumidity = relativeHumidity;
	}
	public String getDisasterInfo() {
		return disasterInfo;
	}
	public void setDisasterInfo(String disasterInfo) {
		this.disasterInfo = disasterInfo;
	}
	
	public static void main(String[] args) throws IOException{
		String filename  = Env.getEnv().WorkingDir + "/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/agriculture_forcast_24hours_20150619.txt";
		HashMap <Integer, AgriDisForcast> _alladf = AgriDisForcast.parseForcastFile(filename);
		Iterator<Integer> iter = _alladf.keySet().iterator();  
	    while(iter.hasNext()){    
	       Integer key = iter.next();  
	       AgriDisForcast adf = _alladf.get(key);
	       System.out.println(key + ":" + adf.getDate() + "\t" + adf.getAvgTemp() + "\t" + adf.getMaxTemp() + "\t" + adf.getMinTemp() + "\t" + adf.getWindSpeed() + "\t"
	    		   + adf.getRelativeHumidity() + "\t" + adf.getDisasterInfo());  
	    }
		
	}
}
