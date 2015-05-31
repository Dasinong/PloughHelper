package com.dasinong.ploughHelper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class CPProduct implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long cPProductId;
	private String cPProductName;
	private Set<PetSolu> petSolus = new HashSet<PetSolu>();
	
	private String activeIngredient;
	private String type;
	private String crop;
	private String disease;
	private String volume;
	private String guideline;
	private String tip;
	
	public CPProduct(){}

	public CPProduct(String cPProductName, String activeIngredient,
			String type, String crop, String disease, String volume,
			String guideline, String tip) {
		super();
		this.cPProductName = cPProductName;
		this.activeIngredient = activeIngredient;
		this.type = type;
		this.crop = crop;
		this.disease = disease;
		this.volume = volume;
		this.guideline = guideline;
		this.tip = tip;
	}

	public Long getcPProductId() {
		return cPProductId;
	}

	public void setcPProductId(Long cPProductId) {
		this.cPProductId = cPProductId;
	}

	public String getcPProductName() {
		return cPProductName;
	}

	public void setcPProductName(String cPProductName) {
		this.cPProductName = cPProductName;
	}

	public Set<PetSolu> getPetSolus() {
		return petSolus;
	}

	public void setPetSolus(Set<PetSolu> petSolus) {
		this.petSolus = petSolus;
	}

	public String getActiveIngredient() {
		return activeIngredient;
	}

	public void setActiveIngredient(String activeIngredient) {
		this.activeIngredient = activeIngredient;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCrop() {
		return crop;
	}

	public void setCrop(String crop) {
		this.crop = crop;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public String getGuideline() {
		return guideline;
	}

	public void setGuideline(String guideline) {
		this.guideline = guideline;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getVolume() {
		return volume;
	}

	public void setVolume(String volume) {
		this.volume = volume;
	}

	


}
