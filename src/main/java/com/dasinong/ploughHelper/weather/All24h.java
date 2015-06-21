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

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class All24h {
private static All24h all24h;
	
	public static All24h getLocation() throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException{
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
	
	private void loadContent() throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException {
		TwentyFourHourForcast tfhf=null;
	
		//File f = new File("/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/MonitorLocation.txt");
        String basefolder = "E:/git/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/current";
		File f = new File("E:/git/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/current");
		if (f.isDirectory()){
			String[] filelist = f.list();
			for(int i=0; i<filelist.length; i++){
				tfhf = new TwentyFourHourForcast(basefolder+"/"+filelist[i],Integer.parseInt(filelist[i]));
				_all24h.put(tfhf.code, tfhf);
			}
		}

	}
	private static HashMap<Integer,TwentyFourHourForcast> _all24h;
	
	public static void main(String[] args) throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException{
		Iterator iter= All24h.getLocation()._all24h.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(((TwentyFourHourForcast) entry.getValue()).info[1].temperature);
		}
	}
}
