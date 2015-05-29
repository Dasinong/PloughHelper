package com.dasinong.ploughHelper.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class SubStage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Long subStageId;
	private String subStageName;
	private String stageName;
    private Set<Variety> varieties = new HashSet<Variety>();
    private Set<NatDisSpec> natDisSpecs = new HashSet<NatDisSpec>();
    private Set<PetDisSpec> petDisSpecs = new HashSet<PetDisSpec>();
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
	public Set<TaskSpec> getTaskSpecs() {
		return taskSpecs;
	}
	public void setTaskSpecs(Set<TaskSpec> taskSpecs) {
		this.taskSpecs = taskSpecs;
	}
	public Set<NatDisSpec> getNatDisSpecs() {
		return natDisSpecs;
	}
	public void setNatDisSpecs(Set<NatDisSpec> natDisSpecs) {
		this.natDisSpecs = natDisSpecs;
	}
	public Set<PetDisSpec> getPetDisSpecs() {
		return petDisSpecs;
	}
	public void setPetDisSpecs(Set<PetDisSpec> petDisSpecs) {
		this.petDisSpecs = petDisSpecs;
	}
  

}
