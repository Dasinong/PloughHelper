package com.dasinong.ploughHelper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Location implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long locationId;
	private String locationName;
	private Set<Field> fields = new HashSet<Field>();
	private Set<NatDisSpec> natDisSpecs = new HashSet<NatDisSpec>();
	private Set<PetDisSpec> petDisSpecs = new HashSet<PetDisSpec>();
	private String other;
	
	public Location(){
		
	}
	public Location(String locationName){
		this.locationName = locationName;
	}
	
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
	public Set<NatDisSpec> getNatDisSpecs() {
		return natDisSpecs;
	}
	public void setNatDisSpecs(Set<NatDisSpec> natDisSpecs) {
		this.natDisSpecs = natDisSpecs;
	}
	public Set<PetDisSpec> getPetDisSpecs() {
		return petDisSpecs;
	}
	public void setPetDisSpecs(Set<PetDisSpec> petDisSpecs) {
		this.petDisSpecs = petDisSpecs;
	}

	
	
	
}
