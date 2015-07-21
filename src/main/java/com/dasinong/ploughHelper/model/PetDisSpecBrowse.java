package com.dasinong.ploughHelper.model;

import java.io.Serializable;

public class PetDisSpecBrowse implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long petDisSpecId;
	private String type ;
	private String petDisSpecName;
	private String petDisSpecNamePY;
	public long getPetDisSpecId() {
		return petDisSpecId;
	}
	public void setPetDisSpecId(long petDisSpecId) {
		this.petDisSpecId = petDisSpecId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPetDisSpecName() {
		return petDisSpecName;
	}
	public void setPetDisSpecName(String petDisSpecName) {
		this.petDisSpecName = petDisSpecName;
	}
	public String getPetDisSpecNamePY() {
		return petDisSpecNamePY;
	}
	public void setPetDisSpecNamePY(String petDisSpecNamePY) {
		this.petDisSpecNamePY = petDisSpecNamePY;
	}
	
}
