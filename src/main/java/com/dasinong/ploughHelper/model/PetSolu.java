package com.dasinong.ploughHelper.model;

import java.io.Serializable;


//PestDisasterSolution
public class PetSolu implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long petSoluId;
	private String petSoluName;
	private PetDisSpec petDisSpec;
	private boolean cure; //true for cure, false for prevent
	
	private String other;

	public Long getPetSoluId() {
		return petSoluId;
	}

	public void setPetSoluId(Long petSoluId) {
		this.petSoluId = petSoluId;
	}

	public String getPetSoluName() {
		return petSoluName;
	}

	public void setPetSoluName(String petSoluName) {
		this.petSoluName = petSoluName;
	}

	public boolean getCure() {
		return cure;
	}

	public void setCure(boolean cure) {
		this.cure = cure;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public PetDisSpec getPetDisSpec() {
		return petDisSpec;
	}

	public void setPetDisSpec(PetDisSpec petDisSpec) {
		this.petDisSpec = petDisSpec;
	}
}
