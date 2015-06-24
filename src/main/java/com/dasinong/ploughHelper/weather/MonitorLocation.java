package com.dasinong.ploughHelper.weather;


//Hard-coded to increase performance
public class MonitorLocation {
    public String city;
    public int postCode;
    public String cityDetial;
    public double latitude;
    public double longitude;
    public int code;
    
	public MonitorLocation(String city, int postCode, String cityDetial,
			double latitude, double longitude, int code) {
		super();
		this.city = city;
		this.postCode = postCode;
		this.cityDetial = cityDetial;
		this.latitude = latitude;
		this.longitude = longitude;
		this.code = code;
	}
    
	public MonitorLocation(String city, String postCode, String cityDetail,
			String latitude, String longitude, String code) {
		super();
		try{
		this.city = city;
		if (postCode!=null && !postCode.equals("")){
			this.postCode = Integer.parseInt(postCode);
		}
		this.cityDetial = cityDetail;
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
		this.code = Integer.parseInt(code);
		}
		catch(Exception e){
			System.out.println("Initialize failed for "+ city +","+postCode+"," + cityDetail+"," + 
		       latitude+","+longitude+","+code);
		}
	}
    
		  
}
