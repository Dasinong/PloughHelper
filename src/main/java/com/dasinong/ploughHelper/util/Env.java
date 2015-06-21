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
			BaseFTP = "E:/git/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/";
			BaseDATA = "E:/git/PloughHelper/src/main/java/com/dasinong/ploughHelper/weather/";
		}
		else{
			BaseFTP = "/data/data/ftp";
			BaseDATA = "/data/data/weather";
		}
		
	}
	
	public String BaseFTP;
	public String BaseDATA;
	
	public static void main(String[] args){
		System.out.println(Env.getEnv().BaseFTP);
		System.out.println(Env.getEnv().BaseDATA);
	}
}
