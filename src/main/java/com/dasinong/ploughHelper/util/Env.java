package com.dasinong.ploughHelper.util;



public class Env {
	private static Env env;
	
	public static Env getEnv(){
		if (env==null){
			env = new Env();
			return env;
		}
		else return env;
		
	}
	private Env() {
		if (System.getProperty("os.name").equalsIgnoreCase("windows 7")){
			WorkingDir = "E:/git";
			DataDir = "E:/index";
			if (System.getProperty("user.name").equalsIgnoreCase("Jason Wu")) {
				WorkingDir = "C:/Users/Jason Wu/workspace";
				DataDir = "G:/index";
			} else if(System.getProperty("user.name").equalsIgnoreCase("Dell")){
				WorkingDir = "G:/Data/workbench-0703";
				DataDir = "G:/index";
			}
			BaseFTP = WorkingDir + "/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/";
			BaseDATA =WorkingDir + "/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/";
			weatherAlert=true;
		}
		else{
			WorkingDir = "/data";
			DataDir = "/usr/local/tomcat7/webapps/";
			BaseFTP = WorkingDir + "/data/ftp";
			BaseDATA =WorkingDir + "/data/weather";
			weatherAlert =false;
		}
	}
	
	public String BaseFTP;
	public String BaseDATA;
	public String WorkingDir;
	public String DataDir;
	public boolean isDebug;
	public boolean weatherAlert;
	
	public static void main(String[] args){
		System.out.println(Env.getEnv().WorkingDir);
		System.out.println(Env.getEnv().BaseFTP);
		System.out.println(Env.getEnv().BaseDATA);
	}
}
