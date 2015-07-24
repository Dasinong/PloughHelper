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

import org.springframework.web.context.ContextLoader;

import com.dasinong.ploughHelper.util.Env;
import com.dasinong.ploughHelper.util.SmsService;

public class AllCurrentJiwen implements IWeatherBuffer{
	private static AllCurrentJiwen allCurrentJiwen;
	
	Date timeStamp = new Date(10000000);
	
	public static AllCurrentJiwen getCurJiwen(){
		if (allCurrentJiwen==null){
			//allCurrentJiwen = new AllCurrentJiwen();
			allCurrentJiwen = (AllCurrentJiwen) ContextLoader.getCurrentWebApplicationContext().getBean("allCurrentJiwen");
			return allCurrentJiwen;
		}
		else{
			return allCurrentJiwen;
		}
	}

	private AllCurrentJiwen(){
		_allCurrentJiwen = new HashMap<Integer,Integer>();
		try{
			loadContent(latestSourceFile());
		}catch(Exception e){
			System.out.println("Initialize current Jiwen failed.");
			SmsService.weatherAlert("Initialize jiwen failed on " + new Date() + " with file " + latestSourceFile());
		}
	}
	
	//自动更新
	@Override
	public void updateContent(){
		updateContent(latestSourceFile());
	}
	
	//强制更新
	@Override
	public void updateContent(String sourceFile){
		HashMap<Integer,Integer> oldJiwen = _allCurrentJiwen;
		_allCurrentJiwen = new HashMap<Integer,Integer>();
		try{
			loadContent(sourceFile);
		}catch(Exception e){
			System.out.println("update jiwen failed. " +  e.getCause());
			SmsService.weatherAlert("Update jiwen failed on " + new Date() + " with file " + sourceFile);
			_allCurrentJiwen = oldJiwen;			
		}
	}
	
	private String latestSourceFile(){
		String sourceFile;
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
			sourceFile = Env.getEnv().WorkingDir+"/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/jw_2015-6-17.csv";
	    }else{
	       	Date date = new Date();
	       	date.setTime(date.getTime()-24*60*60*1000);
	       	String filename = "";
	       	SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
	       	filename = "jw_"+df.format(date)+".csv";
	       	sourceFile = Env.getEnv().WorkingDir+"/data/ftp/jiwen/"+filename;
	    }
		System.out.println(sourceFile);
		return sourceFile;
	}
	
	private void loadContent(String sourceFile) throws IOException, ParseException {
   
		File f = new File(sourceFile);
		FileInputStream fr = new FileInputStream(f);
		
		StringBuilder notification = new StringBuilder();
		notification.append("load jiwen on " + new Date()+". Issue loading: "); 
		
		BufferedReader br = new BufferedReader(new InputStreamReader(fr,"UTF-8"));
		String line;
		line = br.readLine();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-M-d");
		String[] units = line.split(",");
		
		_allCurrentJiwen.put(Integer.parseInt(units[0]), Integer.parseInt(units[2]));
		timeStamp = df.parse(units[1]);
		while ((line=br.readLine())!=null) {
			line = line.trim();
			try{
				units = line.split(",");
				_allCurrentJiwen.put(Integer.parseInt(units[0]), Integer.parseInt(units[2]));
			}catch (Exception e){
				System.out.println("Error happend while inserting jiwen "+ line);
				notification.append(line.substring(0,Math.min(line.length(),10))+" ");
			}
		}
		String sms = notification.substring(0,Math.min(notification.length(),  SmsService.maxLength));
		SmsService.weatherAlert(sms);
		br.close();
		fr.close();
	}
	private HashMap<Integer,Integer> _allCurrentJiwen;
	
	@Override
	public String latestUpdate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
		return df.format(timeStamp); 
	}
	
	public static void main(String[] args) throws IOException, ParseException{
		Iterator iter= AllCurrentJiwen.getCurJiwen()._allCurrentJiwen.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.print(entry.getKey()+": ");
			System.out.println(entry.getValue());
		}
	}
}
