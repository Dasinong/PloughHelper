package com.dasinong.ploughHelper.model;

import java.io.Serializable;

public class Step implements Serializable{
		
	private static final long serialVersionUID = 1L;
		
	private Long stepId;
	private String stepName;
	private TaskSpec taskSpec;
	
	private String description;
	private String picture;
	
	public Step(){}
	public Step(String stepName, TaskSpec taskSpec){
		this.stepName = stepName;
		this.taskSpec = taskSpec;
	}
	
	public Long getStepId() {
		return stepId;
	}
	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public TaskSpec getTaskSpec() {
		return taskSpec;
	}
	public void setTaskSpec(TaskSpec taskSpec) {
		this.taskSpec = taskSpec;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
