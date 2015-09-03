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
	
	
	public SystemMessage(int id, int monitorLocation, String locationGroup, String channel, String content,
			Date startTime, Date endTime) {
		super();
		this.id = id;
		this.monitorLocation = monitorLocation;
		this.locationGroup = locationGroup;
		this.channel = channel;
		this.content = content;
		this.startTime = startTime;
		this.endTime = endTime;
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

}
