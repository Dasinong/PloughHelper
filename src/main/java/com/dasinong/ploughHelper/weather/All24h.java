package com.dasinong.ploughHelper.weather;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class All24h {
	private static All24h all24h;
	
	public static All24h get24h() throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException{
		if (all24h==null){
			all24h = new All24h();
			return all24h;
		}
		else{
			return all24h;
		}
		
	}
	private All24h() throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException{
		_all24h = new HashMap<Integer,TwentyFourHourForcast>();
		loadContent();
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
	
	private void loadContent() throws IOException, ParseException, NumberFormatException, ParserConfigurationException {
		TwentyFourHourForcast tfhf=null;
	
		//File f = new File("/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/MonitorLocation.txt");
        String basefolder="";
        if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
        	basefolder = "E:/git/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/current";
        }else{
        	basefolder = "/data/data/weather/hour/current";
        }
        
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
