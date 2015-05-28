package com.dasinong.ploughHelper.model;

public class QualityItem {
	
	private Long qualityItemId;
	private String qualityItemName;
	private Crop crop;
	public Long getQualityItemId() {
		return qualityItemId;
	}
	public void setQualityItemId(Long qualityItemId) {
		this.qualityItemId = qualityItemId;
	}
	public String getQualityItemName() {
		return qualityItemName;
	}
	public void setQualityItemName(String qualityItemName) {
		this.qualityItemName = qualityItemName;
	}
	public Crop getCrop() {
		return crop;
	}
	public void setCrop(Crop crop) {
		this.crop = crop;
	}

}
