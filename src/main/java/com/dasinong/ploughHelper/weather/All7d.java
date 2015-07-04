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

import com.dasinong.ploughHelper.util.Env;

public class All7d {
	private static All7d all7d;
	private static String WorkingDir;
	
	public static All7d getAll7d() throws IOException, ParseException{
		if (all7d==null){
			all7d = new All7d();
			return all7d;
		}
		else{
			return all7d;
		}
		
	}
	private All7d() throws IOException, ParseException{
		_all7d = new HashMap<Integer,SevenDayForcast>();
		Env env = new Env();
		WorkingDir = env.WorkingDir;
		loadContent();
	}
    public void updateContent() throws IOException, ParseException{
     	HashMap<Integer,SevenDayForcast> old7d = _all7d;
    	_all7d = new HashMap<Integer,SevenDayForcast>();
		try{
			loadContent();
		}
		catch(Exception e){
			System.out.println("update 7d failed. " +  e.getCause());
			_all7d = old7d;
		}
    }
	
	private void loadContent() throws IOException, ParseException {
		SevenDayForcast sdf=null;
		//File f = new File("/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/MonitorLocation.txt");
		
        String fullpath="";
        String fullpathb="";
        if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
        	fullpath = WorkingDir + "/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/rforcast_7days_2015061720.csv";
        }else{
        	Date date = new Date();
        	String filename = "";
        	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        	if (date.getHours()<=9){
        		date.setDate(date.getDate()-1);
        		filename = "rforcast_7days_"+df.format(date)+"20.csv";
        	}
        	else if (date.getHours()<=20) {
        		filename = "rforcast_7days_"+df.format(date)+"09.csv";
        	}
        	else{
        		filename = "rforcast_7days_"+df.format(date)+"20.csv";
        	}
        	fullpath = WorkingDir + "/data/ftp/rforecast7days/"+filename;
        }
		
		File f = new File(fullpath);
		
		FileInputStream fr = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fr,"UTF-8"));
		String line;
		br.readLine();
		int currentCode =0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		while ((line=br.readLine())!=null) {
			line = line.trim();
			try{
				String units[] = line.split("\t");
				if (units.length==9){
					int code = Integer.parseInt(units[0]);
					Date forcast_time = df.parse(units[1]);
					short weather = Short.parseShort(units[2]);
					double temp = Double.parseDouble(units[3]);
					double max_temp = Double.parseDouble(units[4]);
					double min_temp = Double.parseDouble(units[5]);
					short ff_level = Short.parseShort(units[6]);
					short dd_level = Short.parseShort(units[7]);
					double rain = Double.parseDouble(units[8]);
					if (code!=currentCode){
						sdf = new SevenDayForcast(code,forcast_time);
						currentCode = code;
						sdf.addRawData(forcast_time, weather, temp, max_temp, min_temp, ff_level, dd_level, rain);
						_all7d.put(code, sdf);
					}
					else{
						sdf.addRawData(forcast_time, weather, temp, max_temp, min_temp, ff_level, dd_level, rain);
					}
				}
			}catch (Exception e){
				System.out.println("Error happend while inserting 7 day forcast "+ line);
			}
		}
		br.close();
		fr.close();
	}
	private HashMap<Integer,SevenDayForcast> _all7d;
	
	public SevenDayForcast get7d(Integer aid){
		return _all7d.get(aid);
	}
	

	
	public static void main(String[] args) throws IOException, ParseException{
		/*
		Iterator iter= All7d.getAll7d()._all7d.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(entry.getValue());
		}
		All7d.getAll7d().get7d(101090301);
		*/
	}
}
