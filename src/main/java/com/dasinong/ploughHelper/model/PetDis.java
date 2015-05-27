package com.dasinong.ploughHelper.model;

import java.util.HashSet;
import java.util.Set;


public class PetDis {
	private static final long serialVersionUID = 1L;
	
	private Long petDisId;
	private String petDisName;
	private Set<SubStage> subStages = new HashSet<SubStage>();
	private Set<Location> locations = new HashSet<Location>();
	public Long getPetDisId() {
		return petDisId;
	}
	public void setPetDisId(Long petDisId) {
		this.petDisId = petDisId;
	}
	public String getPetDisName() {
		return petDisName;
	}
	public void setPetDisName(String petDisName) {
		this.petDisName = petDisName;
	}
	public Set<SubStage> getSubStages() {
		return subStages;
	}
	public void setSubStages(Set<SubStage> subStages) {
		this.subStages = subStages;
	}
	public Set<Location> getLocations() {
		return locations;
	}
	public void setLocations(Set<Location> locations) {
		this.locations = locations;
	}
	
	
}
