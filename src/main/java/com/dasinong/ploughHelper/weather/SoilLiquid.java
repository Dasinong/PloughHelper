package com.dasinong.ploughHelper.weather;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

public class SoilLiquid {
private static SoilLiquid soilLiquid;
	
	public static SoilLiquid getSoilLi() throws IOException, ParseException{
		if (soilLiquid==null){
			soilLiquid = new SoilLiquid();
			return soilLiquid;
		}
		else{
			return soilLiquid;
		}
	}
	
	private SoilLiquid() throws IOException, ParseException{
		loadContent();
	}
	
	private void loadContent() throws IOException, ParseException {
		SevenDayHumidity sdh=null;
		//File f = new File("/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/MonitorLocation.txt");
		File f = new File("./src/main/java/com/dasinong/ploughHelper/weather/soilliquid_2015061700.txt");
		FileInputStream fr = new FileInputStream(f);
		BufferedReader br = new BufferedReader(new InputStreamReader(fr,"UTF-8"));
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
	
	
	private double getSoil(double lat,double lon){
		double result=0;
		//1. locate lat, in +-1.5; 
		int start;
		int end;
		int i=0;
		while((lat-grid[i][1])>1.5){
			i=i+100;
		}
		start =i;
		while((grid[i][1]-lat)<1.5){
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
