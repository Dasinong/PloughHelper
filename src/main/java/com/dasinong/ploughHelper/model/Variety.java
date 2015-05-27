package com.dasinong.ploughHelper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Variety implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long varietyId;
	private String varietyName;
	private Set<Field> fields = new HashSet<Field>();
	private Set<SubStage> subStages = new HashSet<SubStage>();
	private Crop crop;
	private String other;
	
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
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
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
}
