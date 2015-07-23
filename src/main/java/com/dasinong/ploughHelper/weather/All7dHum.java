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

public class All7dHum implements IWeatherBuffer {
	private static All7dHum all7dHum;
	
	public static All7dHum get7dHum(){
		if (all7dHum==null){
			//all7dHum = new All7dHum();
			all7dHum = (All7dHum) ContextLoader.getCurrentWebApplicationContext().getBean("all7dHum");
			return all7dHum;
		}
		else{
			return all7dHum;
		}
	}
	
	private All7dHum(){
		_all7dHum = new HashMap<Integer,SevenDayHumidity>();
		try{
			loadContent(latestSourceFile());
		}catch(Exception e){
			System.out.println("Initialize 7d hum failed");
		}
	}
	
	@Override
	public void updateContent(){
		updateContent(latestSourceFile());
	}

	@Override
	public void updateContent(String sourceFile) {
		HashMap<Integer,SevenDayHumidity> old7dHum = _all7dHum;
		_all7dHum = new HashMap<Integer,SevenDayHumidity>();
		try{
			loadContent(sourceFile);
		}catch(Exception e){
			System.out.println("update 7d hum failed. " +  e.getCause());
			_all7dHum = old7dHum;			
		}
	}
	
	private String latestSourceFile(){
		String sourceFile;
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
			sourceFile = Env.getEnv().WorkingDir + "/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/rehumidity_7days_2015061908.csv";
	    }else{
	       	Date date = new Date();
	       	String filename = "";
	       	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
	       	if (date.getHours()<9){
	       		date.setTime(date.getTime()-24*60*60*1000);
	       		filename = "rehumidity_7days_"+df.format(date)+"18.csv";
	       	}
	       	if (date.getHours()<19){
	       		filename = "rehumidity_7days_"+df.format(date)+"08.csv";
	       	}
	       	else{
	       		filename = "rehumidity_7days_"+df.format(date)+"18.csv";
	       	}
	       	sourceFile = Env.getEnv().WorkingDir + "/data/ftp/rehumidity/"+filename;
	    }
		System.out.println(sourceFile);
		return sourceFile;
	}
	
	private void loadContent(String sourceFile) throws IOException{
		SevenDayHumidity sdh=null;
	    File f = new File(sourceFile);
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
	
	public SevenDayHumidity getSevenDayHumidity(Integer id){
		return _all7dHum.get(id);
	}
	
	@Override
	public String latestUpdate(){
		SevenDayHumidity sdh = this._all7dHum.get(101010100);
		if (sdh!=null){
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
			return df.format(sdh.startDate); 
		}
		else return "No data found. Check whether initialize failed.";
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
