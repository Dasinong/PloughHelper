package com.dasinong.ploughHelper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class TaskSpec implements Serializable{
		
	private static final long serialVersionUID = 1L;
		
	private Long taskSpecId;
	private String taskSpecName;
	private SubStage subStage;
	private Set<Step> steps = new HashSet<Step>();
	private String type;
	private String fitRegion;
	
	public TaskSpec(){}
	public TaskSpec(String taskSpecName, SubStage subStage){
		this.taskSpecName = taskSpecName;
		this.subStage = subStage;
	}
	
	public Long getTaskSpecId() {
		return taskSpecId;
	}
	public void setTaskSpecId(Long taskSpecId) {
		this.taskSpecId = taskSpecId;
	}
	public String getTaskSpecName() {
		return taskSpecName;
	}
	public void setTaskSpecName(String taskSpecName) {
		this.taskSpecName = taskSpecName;
	}
	public SubStage getSubStage() {
		return subStage;
	}
	public void setSubStage(SubStage subStage) {
		this.subStage = subStage;
	}
	public Set<Step> getSteps() {
		return steps;
	}
	public void setSteps(Set<Step> steps) {
		this.steps = steps;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFitRegion() {
		return fitRegion;
	}
	public void setFitRegion(String fitRegion) {
		this.fitRegion = fitRegion;
	}

	
}
