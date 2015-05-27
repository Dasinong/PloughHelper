package com.dasinong.ploughHelper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Location implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long locationId;
	private String locationName;
	private Set<Field> fields = new HashSet<Field>();
	private Set<NatDis> natDiss = new HashSet<NatDis>();
	private Set<PetDis> petDiss = new HashSet<PetDis>();
	private String other;
	
	public Long getLocationId() {
		return locationId;
	}
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Set<Field> getFields() {
		return fields;
	}
	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public Set<NatDis> getNatDiss() {
		return natDiss;
	}
	public void setNatDiss(Set<NatDis> natDiss) {
		this.natDiss = natDiss;
	}
	public Set<PetDis> getPetDiss() {
		return petDiss;
	}
	public void setPetDiss(Set<PetDis> petDiss) {
		this.petDiss = petDiss;
	} 
	
	
}
