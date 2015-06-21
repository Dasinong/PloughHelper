package com.dasinong.ploughHelper.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
		//File f = new File("/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/MonitorLocation.txt");
		File f = new File("E:/git/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/MonitorLocation.txt");
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
	private static HashMap<Integer,MonitorLocation> _allLocation;
	
	public static void main(String[] args) throws IOException{
		
		Iterator iter= AllLocation.getLocation()._allLocation.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(entry.getValue());
		}
	}
			   
}