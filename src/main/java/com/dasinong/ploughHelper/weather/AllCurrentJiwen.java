package com.dasinong.ploughHelper.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AllCurrentJiwen {
	private static AllCurrentJiwen allCurrentJiwen;
	
	Date date;
	
	public static AllCurrentJiwen getCurJiwen() throws IOException, ParseException{
		if (allCurrentJiwen==null){
			allCurrentJiwen = new AllCurrentJiwen();
			return allCurrentJiwen;
		}
		else{
			return allCurrentJiwen;
		}
		
	}
	private AllCurrentJiwen() throws IOException, ParseException{
		_allCurrentJiwen = new HashMap<Integer,Integer>();
		loadContent();
	}
	
	private void loadContent() throws IOException, ParseException {
		SevenDayForcast sdf=null;
		//File f = new File("/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/MonitorLocation.txt");
		File f = new File("./src/main/java/com/dasinong/ploughHelper/weather/jw_2015-6-17.csv");
		FileInputStream fr = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fr,"UTF-8"));
		String line;
		line = br.readLine();
		int currentCode =0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String[] units = line.split(",");
		
		_allCurrentJiwen.put(Integer.parseInt(units[0]), Integer.parseInt(units[2]));
	    date = df.parse(units[1]);
		while ((line=br.readLine())!=null) {
			line = line.trim();
			try{
				units = line.split(",");
				_allCurrentJiwen.put(Integer.parseInt(units[0]), Integer.parseInt(units[2]));
				if (units.length==3){
					
				}
			}catch (Exception e){
				System.out.println("Error happend while inserting jiwen "+ line);
			}
		}
		br.close();
		fr.close();
	}
	private static HashMap<Integer,Integer> _allCurrentJiwen;
	
	public static void main(String[] args) throws IOException, ParseException{
		Iterator iter= AllCurrentJiwen.getCurJiwen()._allCurrentJiwen.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(entry.getValue());
		}
	}
}
