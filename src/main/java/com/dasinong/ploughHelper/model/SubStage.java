package com.dasinong.ploughHelper.model;

import java.util.HashSet;
import java.util.Set;

public class SubStage {
	private static final long serialVersionUID = 1L;
	
	private Long subStageId;
	private String subStageName;
	private String stageName;
    private Set<Variety> varieties = new HashSet<Variety>();
    private Set<NatDis> natDiss = new HashSet<NatDis>();
    private Set<PetDis> petDiss = new HashSet<PetDis>();
    private Set<TaskSpec> taskSpecs = new HashSet<TaskSpec>();

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
	public Set<Variety> getVarieties() {
		return varieties;
	}
	public void setVarieties(Set<Variety> varieties) {
		this.varieties = varieties;
	}
	public Set<NatDis> getNatDiss() {
		return natDiss;
	}
	public void setNatDiss(Set<NatDis> natDiss) {
		this.natDiss = natDiss;
	}
	public Set<PetDis> getPetDiss() {
		return petDiss;
	}
	public void setPetDiss(Set<PetDis> petDiss) {
		this.petDiss = petDiss;
	}
	public Set<TaskSpec> getTaskSpecs() {
		return taskSpecs;
	}
	public void setTaskSpecs(Set<TaskSpec> taskSpecs) {
		this.taskSpecs = taskSpecs;
	}
  

}
