package com.dasinong.ploughHelper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


public class PetDisSpec implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long petDisSpecId;
	private String petDisSpecName;
	private Set<SubStage> subStages = new HashSet<SubStage>();
	private Set<Location> locations = new HashSet<Location>();
	private Set<PetSolu> petSolus = new HashSet<PetSolu>();
	
	public PetDisSpec(){}
	public PetDisSpec(String petDisSpecId){
		this.petDisSpecName = petDisSpecId;
	}
	
	public Long getPetDisSpecId() {
		return petDisSpecId;
	}
	public void setPetDisSpecId(Long petDisSpecId) {
		this.petDisSpecId = petDisSpecId;
	}
	public String getPetDisSpecName() {
		return petDisSpecName;
	}
	public void setPetDisSpecName(String petDisSpecName) {
		this.petDisSpecName = petDisSpecName;
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
	public Set<PetSolu> getPetSolus() {
		return petSolus;
	}
	public void setPetSolus(Set<PetSolu> petSolus) {
		this.petSolus = petSolus;
	}
	
	
	
}
