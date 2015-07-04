package com.dasinong.ploughHelper.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.dasinong.ploughHelper.util.Env;

public class AllLocation {
	
	private static AllLocation allLocation;
	
	public static AllLocation getLocation() throws IOException{
		if (allLocation==null){
			allLocation = new AllLocation();
			return allLocation;
		}
		else{
			return allLocation;
		}
	}
	
	private AllLocation() throws IOException{
		_allLocation = new HashMap<Integer,MonitorLocation>();
		loadContent();
	}
	
	private void loadContent() throws IOException {
		MonitorLocation m;
	
		String fullpath="";
	    if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
	       	fullpath = Env.getEnv().WorkingDir+"/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/MonitorLocation.txt";
	    }else{
	       	fullpath = Env.getEnv().WorkingDir+"/data/ftp/monitorLocation";
	    }
	    
		File f = new File(fullpath);
		FileInputStream fr = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fr,"UTF-8"));
		String line;
		while ((line=br.readLine())!=null) {
			try{
				line = line.trim();
				String units[] = line.split(" ");
				if (units.length==6){
					m = new MonitorLocation(units[0],units[1],units[2],units[3],units[4],units[5]);
					_allLocation.put(m.code, m);
				}
			}catch (Exception e){
				System.out.println("Error happend while inserting monitor location " + line);
			}
		}
		br.close();
		fr.close();
	}
	public HashMap<Integer,MonitorLocation> _allLocation;
	
	public int getNearest(double lat,double lon){
		MonitorLocation target = null;
		double minDis =100;
		Iterator iter= _allLocation.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<Integer,MonitorLocation> entry = (Map.Entry<Integer,MonitorLocation>) iter.next();
			MonitorLocation ml = entry.getValue();
			if ((ml.latitude-lat)*(ml.latitude-lat) +(ml.longitude-lon)*(ml.longitude-lon)<minDis){
				minDis = (ml.latitude-lat)*(ml.latitude-lat) +(ml.longitude-lon)*(ml.longitude-lon);
				target = ml;
			}
		}
		return target.code;
	}
	
	public MonitorLocation getMonitorLocation(int areaid){
		return _allLocation.get(areaid);
	}
	
	public static void main(String[] args) throws IOException{
		
		Iterator iter= AllLocation.getLocation()._allLocation.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(entry.getValue());
		}
		
		System.out.print(AllLocation.getLocation().getNearest(39,116));
	}
			   
}
