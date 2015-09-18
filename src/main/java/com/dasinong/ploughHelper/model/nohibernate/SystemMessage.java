package com.dasinong.ploughHelper.model.nohibernate;

import java.util.Date;

public class SystemMessage {
	private int id;
	private int monitorLocation;
	private String locationGroup;
	private String channel;
	private String content;
	private Date startTime;
	private Date endTime;
	private String picUrl;
	private String landingUrl;
	
	
	public SystemMessage(int id, int monitorLocation, String locationGroup, String channel, String content,
			Date startTime, Date endTime,String picUrl,String landingUrl) {
		super();
		this.id = id;
		this.monitorLocation = monitorLocation;
		this.locationGroup = locationGroup;
		this.channel = channel;
		this.content = content;
		this.startTime = startTime;
		this.endTime = endTime;
		this.picUrl = picUrl;
		this.landingUrl = landingUrl;
	}
	public String getLocationGroup() {
		return locationGroup;
	}
	public void setLocationGroup(String locationGroup) {
		this.locationGroup = locationGroup;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMonitorLocation() {
		return monitorLocation;
	}
	public void setMonitorLocation(int monitorLocation) {
		this.monitorLocation = monitorLocation;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getLandingUrl() {
		return landingUrl;
	}
	public void setLandingUrl(String landingUrl) {
		this.landingUrl = landingUrl;
	}

}
