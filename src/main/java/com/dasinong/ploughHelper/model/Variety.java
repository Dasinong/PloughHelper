package com.dasinong.ploughHelper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Variety implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long varietyId;
	private String varietyName;
	private String subId;
	private Set<Field> fields = new HashSet<Field>();
	private Set<SubStage> subStages = new HashSet<SubStage>();
	private Crop crop;
	private Map<Long,QualityItemValue> qualityItemValues;

    private String type;
    private String genoType;
    private String maturityType;
    private String suitableArea;
    private int totalAccumulatedTempNeeded;
    private int fullCycleDuration;
    private int typicalYield;
    private String owner;
    private String nationalStandard;
    private int yearofReg;
    
	
	public Variety (){}
	
	public Variety (String varietyName, Crop crop){
		this.varietyName = varietyName;
		this.crop = crop;
	}
	
	public Long getVarietyId() {
		return varietyId;
	}
	public void setVarietyId(Long varietyId) {
		this.varietyId = varietyId;
	}
	public String getVarietyName() {
		return varietyName;
	}
	public void setVarietyName(String varietyName) {
		this.varietyName = varietyName;
	}
	public Set<Field> getFields() {
		return fields;
	}
	public void setFields(Set<Field> fields) {
		this.fields = fields;
	}

	public Crop getCrop() {
		return crop;
	}
	public void setCrop(Crop crop) {
		this.crop = crop;
	}
	
	public Set<SubStage> getSubStages() {
		return subStages;
	}
	public void setSubStages(Set<SubStage> subStages) {
		this.subStages = subStages;
	}
	public Map<Long,QualityItemValue> getQualityItemValues() {
		return qualityItemValues;
	}
	public void setQualityItemValues(Map<Long,QualityItemValue> qualityItemValues) {
		this.qualityItemValues = qualityItemValues;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGenoType() {
		return genoType;
	}

	public void setGenoType(String genoType) {
		this.genoType = genoType;
	}

	public String getMaturityType() {
		return maturityType;
	}

	public void setMaturityType(String maturityType) {
		this.maturityType = maturityType;
	}

	public String getSuitableArea() {
		return suitableArea;
	}

	public void setSuitableArea(String suitableArea) {
		this.suitableArea = suitableArea;
	}

	public int getTotalAccumulatedTempNeeded() {
		return totalAccumulatedTempNeeded;
	}

	public void setTotalAccumulatedTempNeeded(int totalAccumulatedTempNeeded) {
		this.totalAccumulatedTempNeeded = totalAccumulatedTempNeeded;
	}

	public int getFullCycleDuration() {
		return fullCycleDuration;
	}

	public void setFullCycleDuration(int fullCycleDuration) {
		this.fullCycleDuration = fullCycleDuration;
	}

	public int getTypicalYield() {
		return typicalYield;
	}

	public void setTypicalYield(int typicalYield) {
		this.typicalYield = typicalYield;
	}

	public String getNationalStandard() {
		return nationalStandard;
	}

	public void setNationalStandard(String nationalStandard) {
		this.nationalStandard = nationalStandard;
	}

	public int getYearofReg() {
		return yearofReg;
	}

	public void setYearofReg(int yearofReg) {
		this.yearofReg = yearofReg;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getSubId() {
		return subId;
	}

	public void setSubId(String subId) {
		this.subId = subId;
	}

}
