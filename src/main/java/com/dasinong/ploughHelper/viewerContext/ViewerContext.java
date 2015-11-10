package com.dasinong.ploughHelper.viewerContext;

import javax.servlet.http.HttpServletRequest;

import com.dasinong.ploughHelper.model.DasinongApp;

public class ViewerContext {
	
	public final static String REQUEST_KEY = "__VIEWER_CONTEXT__";

	private Long userId = null;
	private Long appId = null;
	private String deviceId = null;
	
	public ViewerContext() {}
	
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	
	public Long getUserId() {
		return this.userId;
	}
	
	public Long getAppId() {
		return this.appId;
	}

	public String getDevideId() {
		return this.deviceId;
	}
	
	public boolean isUserLogin() {
		return this.userId != null;
	}
	
	public boolean isAndroidFarmLog() {
		return DasinongApp.ANDROID_FARM_LOG.equals(this.appId);
	}
	
	public boolean isIOSFarmLog() {
		return DasinongApp.IOS_FARM_LOG.equals(this.appId);
	}
}