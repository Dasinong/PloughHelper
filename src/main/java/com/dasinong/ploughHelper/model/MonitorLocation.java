package com.dasinong.ploughHelper.model;

import java.io.Serializable;

public class MonitorLocation implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String city;
    private int postCode;
    private String cityDetail;
    private double latitude;
    private double longitude;
    private int code;
    public MonitorLocation(){
    	
    }
    
	public MonitorLocation(String city, String postCode, String cityDetail, String latitude, String longitude, String code) {
		super();
		this.city = city;
		try{
			this.postCode = Integer.parseInt(postCode);
		}catch(Exception e){}
		this.cityDetail = cityDetail;
		try{
			this.latitude = Double.parseDouble(latitude);
			this.longitude = Double.parseDouble(longitude);
		}catch(Exception e){}
		try{
			this.code = Integer.parseInt(code);
		}catch(Exception e){}
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public int getPostCode() {
		return postCode;
	}
	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}
	public String getCityDetail() {
		return cityDetail;
	}
	public void setCityDetail(String cityDetail) {
		this.cityDetail = cityDetail;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

}
