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

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.util.Env;

public class All7dHum {
	private static All7dHum all7dHum;
	
	public static All7dHum get7dHum() throws IOException, ParseException{
		if (all7dHum==null){
			//all7dHum = new All7dHum();
			all7dHum = (All7dHum) ContextLoader.getCurrentWebApplicationContext().getBean("all7dHum");
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
	public void updateContent() {
		HashMap<Integer,SevenDayHumidity> old7dHum = _all7dHum;
		_all7dHum = new HashMap<Integer,SevenDayHumidity>();
		try{
			loadContent();
		}catch(Exception e){
			System.out.println("update 7d hum failed. " +  e.getCause());
			_all7dHum = old7dHum;			
		}
	}
	private void loadContent() throws IOException, ParseException {
		SevenDayHumidity sdh=null;
		
		
		String fullpath="";
	    if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
	       	fullpath = Env.getEnv().WorkingDir + "/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/rehumidity_7days_2015061908.csv";
	    }else{
	       	Date date = new Date();
	       	String filename = "";
	       	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	       	if (date.getHours()<20){
	       		filename = "rehumidity_7days_"+df.format(date)+"08.csv";
	       	}
	       	else{
	       		filename = "rehumidity_7days_"+df.format(date)+"18.csv";
	       	}
	       	fullpath = Env.getEnv().WorkingDir + "/data/ftp/rehumidity/"+filename;
	    }
	    
	    File f = new File(fullpath);
	    
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
	private HashMap<Integer,SevenDayHumidity> _all7dHum;
	
	public SevenDayHumidity getSevenDayHumidity(Integer id) throws IOException, ParseException{
		return _all7dHum.get(id);
	}
	
	public static void main(String[] args) throws IOException, ParseException{
		Iterator iter= All7dHum.get7dHum()._all7dHum.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(entry.getValue());
		}
	}
}
