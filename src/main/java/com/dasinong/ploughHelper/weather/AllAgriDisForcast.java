package com.dasinong.ploughHelper.weather;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.web.context.ContextLoader;
import org.xml.sax.SAXException;

import com.dasinong.ploughHelper.util.Env;

public class AllAgriDisForcast {

	private static AllAgriDisForcast alladf;
	
	public static AllAgriDisForcast getadf() throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException{
		if (alladf==null){
			try{
				alladf = (AllAgriDisForcast) ContextLoader.getCurrentWebApplicationContext().getBean("alladf");
			} catch (Exception e){
				e.printStackTrace();
				alladf = new AllAgriDisForcast();
			}
			return alladf;
		}
		else{
			return alladf;
		}
		
	}
	private AllAgriDisForcast() throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException{
		_alladf = new HashMap<Integer,AgriDisForcast>();
		loadContent();
	}
	
	public void updateContent(){
		HashMap<Integer,AgriDisForcast> oldadf = _alladf;
		_alladf = new HashMap<Integer, AgriDisForcast>();
		try{
			loadContent();
		}
		catch(Exception e){
			System.out.println("update agriculture disaster forcast failed. " +  e.getCause());
			_alladf = oldadf;
		}
	}
	
	private void loadContent() throws IOException, ParseException, NumberFormatException, ParserConfigurationException {
	
		//File f = new File("/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/agriculture_forcast_24hours_20150619.txt");
        String basefolder="";
        if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
        	basefolder = Env.getEnv().WorkingDir + "/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/";
        }else{
        	basefolder = Env.getEnv().WorkingDir + "/data/ftp/agriculture_forcast/";
        }
        
        DateFormat df = new SimpleDateFormat("yyyyMMdd");
        String filenamePrefix = "agriculture_forcast_24hours_";
        String date  = df.format(new Date());
        String filename = basefolder+filenamePrefix+date+".txt";
        String defaultFilename = basefolder+filenamePrefix+"20150619"+".txt";
		try{
			File f = new File(filename);
			if(!f.exists())
				filename = defaultFilename;
			_alladf = AgriDisForcast.parseForcastFile(filename);
		}catch(Exception e){
			System.out.println("Load agriculture disaster forcast for "+ filename + "failed.");
			System.out.println(e.getCause());
		}
	}
	private HashMap<Integer,AgriDisForcast> _alladf;
	public AgriDisForcast getadf(Integer areaId){
		return _alladf.get(areaId);
	}
	
	public static void main(String[] args) throws IOException, ParseException, NumberFormatException, ParserConfigurationException, SAXException{
		System.out.println(System.getProperty("OS"));		
		Iterator<Entry<Integer, AgriDisForcast>> iter= AllAgriDisForcast.getadf()._alladf.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(((AgriDisForcast) entry.getValue()).getAvgTemp() + " DisInfo : " + ((AgriDisForcast) entry.getValue()).getDisasterInfo());
		}
	}
}
