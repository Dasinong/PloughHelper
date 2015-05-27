package com.dasinong.ploughHelper.model;

import java.util.HashSet;
import java.util.Set;


public class NatDis {
	private static final long serialVersionUID = 1L;
	
	private Long natDisId;
	private String natDisName;
	private Set<SubStage> subStages = new HashSet<SubStage>();
	public Long getNatDisId() {
		return natDisId;
	}
	public void setNatDisId(Long natDisId) {
		this.natDisId = natDisId;
	}
	public String getNatDisName() {
		return natDisName;
	}
	public void setNatDisName(String natDisName) {
		this.natDisName = natDisName;
	}
	public Set<SubStage> getSubStages() {
		return subStages;
	}
	public void setSubStages(Set<SubStage> subStages) {
		this.subStages = subStages;
	}
	
	
}
