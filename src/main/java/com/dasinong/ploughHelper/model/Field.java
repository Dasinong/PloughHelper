package com.dasinong.ploughHelper.model;

import java.io.Serializable;

public class Field implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long fieldId;
	private String fieldName;
	private boolean isActive;
	private Variety variety;
	private Location location;
	
	private String other; 
	
	public Long getFieldId() {
		return fieldId;
	}
	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public Variety getVariety() {
		return variety;
	}
	public void setVariety(Variety variety) {
		this.variety = variety;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
}
