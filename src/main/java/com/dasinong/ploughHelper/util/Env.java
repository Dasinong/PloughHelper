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
			BaseFTP = WorkingDir + "PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/";
			BaseDATA =WorkingDir + "PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/";
		}
		else{
			WorkingDir = "/data";
			BaseFTP = WorkingDir + "/data/ftp";
			BaseDATA =WorkingDir + "/data/weather";
		}
		
	}
	
	public String BaseFTP;
	public String BaseDATA;
	public String WorkingDir;
	
	public static void main(String[] args){
		System.out.println(Env.getEnv().WorkingDir);
		System.out.println(Env.getEnv().BaseFTP);
		System.out.println(Env.getEnv().BaseDATA);
	}
}
