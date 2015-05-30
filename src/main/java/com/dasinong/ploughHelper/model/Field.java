package com.dasinong.ploughHelper.model;

import java.io.Serializable;
import java.util.Map;

public class Field implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long fieldId;
	private String fieldName;
	private boolean isActive;
	private Variety variety;
	private User user;
	private Location location;
	private Map<Long, Task> tasks;
	private Map<Long, PetDis> petDiss;
	private Map<Long, NatDis> natDiss;
	
	private String other; 
	
	public Field(){};
	public Field(String fieldName,Variety variety,User user,Location location){
		this.fieldName=fieldName;
		this.variety = variety;
		this.user = user;
		this.location = location;
	}
	
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
	public Map<Long, Task> getTasks() {
		return tasks;
	}
	public void setTasks(Map<Long, Task> tasks) {
		this.tasks = tasks;
	}
	public Map<Long, PetDis> getPetDiss() {
		return petDiss;
	}
	public void setPetDiss(Map<Long, PetDis> petDiss) {
		this.petDiss = petDiss;
	}
	public Map<Long, NatDis> getNatDiss() {
		return natDiss;
	}
	public void setNatDiss(Map<Long, NatDis> natDiss) {
		this.natDiss = natDiss;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

}
