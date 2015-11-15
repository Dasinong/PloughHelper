package com.dasinong.ploughHelper.outputWrapper;

import com.dasinong.ploughHelper.model.SubStage;

public class SubStageWrapper {

	private Long subStageId;
	private String subStageName;
	private String stageName;

	public SubStageWrapper(SubStage subStage) {
		this.subStageId = subStage.getSubStageId();
		this.subStageName = subStage.getSubStageName();
		this.stageName = subStage.getStageName();
	}

	public Long getSubStageId() {
		return subStageId;
	}

	public void setSubStageId(Long subStageId) {
		this.subStageId = subStageId;
	}

	public String getSubStageName() {
		return subStageName;
	}

	public void setSubStageName(String subStageName) {
		this.subStageName = subStageName;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

}
