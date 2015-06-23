package com.dasinong.ploughHelper.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class All7dHum {
	private static All7dHum all7dHum;
	
	public static All7dHum get7dHum() throws IOException, ParseException{
		if (all7dHum==null){
			all7dHum = new All7dHum();
			return all7dHum;
		}
		else{
			return all7dHum;
		}
		
	}
	private All7dHum() throws IOException, ParseException{
		_all7dHum = new HashMap<Integer,SevenDayHumidity>();
		loadContent();
	}
	
	private void loadContent() throws IOException, ParseException {
		SevenDayHumidity sdh=null;
		//File f = new File("/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/MonitorLocation.txt");
		File f = new File("./src/main/java/com/dasinong/ploughHelper/weather/rehumidity_7days_2015061908.csv");
		FileInputStream fr = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fr,"UTF-8"));
		String line;
		br.readLine();
		int currentCode = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		while ((line=br.readLine())!=null) {
			line = line.trim();
			try{
				String units[] = line.split("\t");
				if (units.length==4){
					int code = Integer.parseInt(units[0]);
					Date forcast_time = df.parse(units[1]);
					int dayHumidity = Integer.parseInt(units[2]);
					int nightHumidity = Integer.parseInt(units[3]);

					if (code!=currentCode){
						sdh = new SevenDayHumidity(code,forcast_time);
						currentCode = code;
						sdh.add(forcast_time, dayHumidity, nightHumidity);
						_all7dHum.put(code, sdh);
					}
					else{
						sdh.add(forcast_time, dayHumidity, nightHumidity);
					}
				}
			}catch (Exception e){
				System.out.println("Error happend while inserting 7 day humidity "+ line);
			}
		}
		br.close();
		fr.close();
	}
	private static HashMap<Integer,SevenDayHumidity> _all7dHum;
	
	
	public static void main(String[] args) throws IOException, ParseException{
		Iterator iter= All7dHum.get7dHum()._all7dHum.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(entry.getValue());
		}
	}
}
