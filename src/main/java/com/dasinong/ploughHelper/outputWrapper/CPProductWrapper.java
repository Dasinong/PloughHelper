package com.dasinong.ploughHelper.outputWrapper;

import com.dasinong.ploughHelper.model.CPProduct;

public class CPProductWrapper {
	Long id;
	String activeIngredient="";
	String name="";
	String type="";
	String disease="";
	String volumn="";
	String guideline="";
	String registrationId="";
	String manufacturer="";
	String tip="";
	
	public CPProductWrapper(CPProduct cp) {
		super();
		this.activeIngredient = cp.getActiveIngredient();
		this.name = cp.getcPProductName();
		this.type = cp.getType();
		this.disease = cp.getDisease();
		this.volumn = cp.getVolume();
		this.guideline = cp.getGuideline();
		this.registrationId = cp.getRegisterationId();
		this.manufacturer = cp.getManufacturer();
		this.tip = cp.getTip();
		this.id = cp.getcPProductId();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getActiveIngredient() {
		return activeIngredient;
	}

	public void setActiveIngredient(String activeIngredient) {
		this.activeIngredient = activeIngredient;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getVolumn() {
		return volumn;
	}

	public void setVolumn(String volumn) {
		this.volumn = volumn;
	}

	public String getGuideline() {
		return guideline;
	}

	public void setGuideline(String guideline) {
		this.guideline = guideline;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}
	
	
	
}
