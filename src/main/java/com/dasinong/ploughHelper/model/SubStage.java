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
    
    private int triggerAccumulatedTemp;
    private int reqMinTemp;
    private int reqAvgTemp;
    private int maxFieldHumidity;
    private int minFieldHumidity;
    private int duration;

    
    
    
    public SubStage(){}
    public SubStage(String subStageName,String stageName){
    	this.subStageName = subStageName;
    	this.stageName = stageName;
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
	public int getTriggerAccumulatedTemp() {
		return triggerAccumulatedTemp;
	}
	public void setTriggerAccumulatedTemp(int triggerAccumulatedTemp) {
		this.triggerAccumulatedTemp = triggerAccumulatedTemp;
	}
	public int getReqMinTemp() {
		return reqMinTemp;
	}
	public void setReqMinTemp(int reqMinTemp) {
		this.reqMinTemp = reqMinTemp;
	}
	public int getReqAvgTemp() {
		return reqAvgTemp;
	}
	public void setReqAvgTemp(int reqAvgTemp) {
		this.reqAvgTemp = reqAvgTemp;
	}
	public int getMaxFieldHumidity() {
		return maxFieldHumidity;
	}
	public void setMaxFieldHumidity(int maxFieldHumidity) {
		this.maxFieldHumidity = maxFieldHumidity;
	}
	public int getMinFieldHumidity() {
		return minFieldHumidity;
	}
	public void setMinFieldHumidity(int minFieldHumidity) {
		this.minFieldHumidity = minFieldHumidity;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
  

}
