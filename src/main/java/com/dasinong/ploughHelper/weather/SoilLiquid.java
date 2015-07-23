package com.dasinong.ploughHelper.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dasinong.ploughHelper.util.Env;

public class SoilLiquid implements IWeatherBuffer{
	private static SoilLiquid soilLiquid;
	private Date timeStamp;
	
	public static SoilLiquid getSoilLi(){
		if (soilLiquid==null){
			soilLiquid = new SoilLiquid();
			return soilLiquid;
		}
		else{
			return soilLiquid;
		}
	}
	
	private SoilLiquid(){
		try{
			loadContent(latestSourceFile());
		}catch(Exception e){
			System.out.println("Initialize soilliquid failed.");
		}
	}
	
	@Override
	public void updateContent(){
		updateContent(latestSourceFile());
	}
	
	@Override
	public void updateContent(String sourceFile) {
		double[][] oldgrid = grid;
		grid = new double[30000][3];
		try{
			loadContent(sourceFile);
		}catch(Exception e){
			System.out.println("update soil liquid failed. " +  e.getCause());
			grid = oldgrid;			
		}
	}
	private String latestSourceFile(){
		String sourceFile;
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
			sourceFile = Env.getEnv().WorkingDir + "/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/soilliquid_2015061700.txt";
		}else{
		  	Date date = new Date();
		   	String filename = "";
		   	SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		   	if (date.getHours()<=12){
		   		date.setTime(date.getTime()-24*60*60*1000);
		   		sourceFile = Env.getEnv().WorkingDir + "/data/ftp/trsd/soilliquid_"+df.format(date)+"00.txt";
		   	}
		   	else{
		   		sourceFile = Env.getEnv().WorkingDir + "/data/ftp/trsd/soilliquid_"+df.format(date)+"00.txt";
		   	}
		}
		System.out.println(sourceFile);
		return sourceFile;
	}
	
	private void loadContent(String sourcefile) throws IOException {
	    File f = new File(sourcefile);
		FileInputStream fr = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fr,"UTF-8"));
		this.timeStamp = new Date();
		String line;
		br.readLine();
		int i=0;
		while ((line=br.readLine())!=null) {
			line = line.trim();
			try{
				String units[] = line.split(",");
				if (units.length==3){
					grid[i][0] = Double.parseDouble(units[0]);
					grid[i][1] = Double.parseDouble(units[1]);
					grid[i][2] = Double.parseDouble(units[2]);
					i++;
				}
			}catch (Exception e){
				System.out.println("Error happend while loading soil liquid "+ line);
			}
		}
		br.close();
		fr.close();
	}
	
	private double[][] grid = new double[30000][3];
	
	public double getSoil(double lat,double lon){
		double result=0;
		//1. locate lat, in +-3; 
		int start;
		int end;
		int i=0;
		while((lat-grid[i][1])>3){
			i=i+100;
		}
		start =i;
		while((grid[i][1]-lat)<3){
			i=i+100;
		}
		end =i;
		double minDis =100;
		System.out.println(start);
		System.out.println(end);
		for (i=start;i<end;i++){
			if ((grid[i][1]-lat)*(grid[i][1]-lat)+(grid[i][0]-lon)*(grid[i][0]-lon)<minDis){
				result = grid[i][2];
				minDis = (grid[i][1]-lat)*(grid[i][1]-lat)+(grid[i][0]-lon)*(grid[i][0]-lon);
			}
		}
		return result;
	}
	
	@Override
	public String latestUpdate(){
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmm");
		return df.format(timeStamp); 
	}
	
	
	public static void main(String[] arsgs) throws IOException, ParseException{
		for (int i=0;i<10000;i++){
			for (int j=0;j<3;j++){
				System.out.print(SoilLiquid.getSoilLi().grid[i][j]+" ");
			}
			System.out.println();
		}
	    System.out.println(SoilLiquid.getSoilLi().getSoil(30.35, 114.35));
		System.out.println(SoilLiquid.getSoilLi().getSoil(30.35, 114.45));
		System.out.println(SoilLiquid.getSoilLi().getSoil(30.35, 114.39));
	}
}
