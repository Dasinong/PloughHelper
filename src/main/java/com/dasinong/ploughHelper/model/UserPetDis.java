package com.dasinong.ploughHelper.model;

import java.io.Serializable;

public class UserPetDis implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String cropName;
	private String disType;
	private String petName;
	private String infectionPart;
	private String impactTime;
	private String impactDis;
	private String operation;
	
	public String getCropName() {
		return cropName;
	}
	public void setCropName(String cropName) {
		this.cropName = cropName;
	}
	public String getDisType() {
		return disType;
	}
	public void setDisType(String disType) {
		this.disType = disType;
	}
	public String getPetName() {
		return petName;
	}
	public void setPetName(String petName) {
		this.petName = petName;
	}
	public String getInfectionPart() {
		return infectionPart;
	}
	public void setInfectionPart(String infectionPart) {
		this.infectionPart = infectionPart;
	}
	public String getImpactTime() {
		return impactTime;
	}
	public void setImpactTime(String impactTime) {
		this.impactTime = impactTime;
	}
	public String getImpactDis() {
		return impactDis;
	}
	public void setImpactDis(String impactDis) {
		this.impactDis = impactDis;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
