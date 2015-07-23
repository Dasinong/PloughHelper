package com.dasinong.ploughHelper.weather;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.context.ContextLoader;
import org.xml.sax.SAXException;

import com.dasinong.ploughHelper.util.Env;

public class All24h {
	private static All24h all24h;
	
	public static All24h get24h(){
		if (all24h==null){
			//all24h = new All24h();
			all24h = (All24h) ContextLoader.getCurrentWebApplicationContext().getBean("all24h");
			return all24h;
		}
		else{
			return all24h;
		}
		
	}
	private All24h(){
		_all24h = new HashMap<Integer,TwentyFourHourForcast>();
		try{
			loadContent();
		}catch(Exception e){
			System.out.println("Initialize 24h failed. " +  e.getCause());
		}
	}
	
	public void updateContent(){
		HashMap<Integer,TwentyFourHourForcast> old24h = _all24h;
		_all24h = new HashMap<Integer,TwentyFourHourForcast>();
		try{
			loadContent();
		}
		catch(Exception e){
			System.out.println("update 24h failed. " +  e.getCause());
			_all24h = old24h;
		}
	}
	
	private void loadContent() {
		System.out.println("loadContent of 24h called " + this.hashCode());
		TwentyFourHourForcast tfhf=null;
	
		//File f = new File("/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/MonitorLocation.txt");
        String basefolder="";
        if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
        	//basefolder = Env.getEnv().WorkingDir+"/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/current";
        	basefolder = "E:/weather/2015072120";
        }else{
        	basefolder = Env.getEnv().WorkingDir +"/data/weather/hour/current";
        	Date date = new Date();
	       	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
        	if (date.getHours()<=7){
        		date.setDate(date.getDate()-1);
        		basefolder = Env.getEnv().WorkingDir +"/data/weather/hour/"+df.format(date)+"20";
        	}
        	else if (date.getHours()<=20 && date.getMinutes()<=15) {
        		basefolder = Env.getEnv().WorkingDir +"/data/weather/hour/"+df.format(date)+"08";
        	}
        	else{
        		basefolder = Env.getEnv().WorkingDir +"/data/weather/hour/"+df.format(date)+"20";
        	}
        }
        try{
			File f = new File(basefolder);
			if (f.isDirectory()){
				String[] filelist = f.list();
				for(int i=0; i<filelist.length; i++){
					try{
						tfhf = new TwentyFourHourForcast(basefolder+"/"+filelist[i],Integer.parseInt(filelist[i]));
						_all24h.put(tfhf.code, tfhf);
					}catch(Exception e){
						System.out.println("Load 24h for "+ filelist[i] + "failed.");
						System.out.println(e.getCause());
					}
				}
			}
        }
		catch(Exception e){
			System.out.println(e.getMessage());
			System.out.println("加载24小时天气失败");
		}

	}
	private HashMap<Integer,TwentyFourHourForcast> _all24h;
	public TwentyFourHourForcast get24h(Integer areaId){
		return _all24h.get(areaId);
	}
	
	public static void main(String[] args) throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException{
		System.out.println(System.getProperty("OS"));
		
		Iterator iter= All24h.get24h()._all24h.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(((TwentyFourHourForcast) entry.getValue()).info[1].temperature);
			System.out.println(((TwentyFourHourForcast) entry.getValue()).info[2].temperature);
			System.out.println(((TwentyFourHourForcast) entry.getValue()).info[3].windDirection_10m);
		}
	}
}
