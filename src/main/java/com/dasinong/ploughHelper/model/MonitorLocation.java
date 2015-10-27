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
    
	public MonitorLocation(String city, int postCode, String cityDetail, double latitude, double longitude, int code) {
		super();
		this.city = city;
		this.postCode = postCode;
		this.cityDetail = cityDetail;
		this.latitude = latitude;
		this.longitude = longitude;
		this.code = code;
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
