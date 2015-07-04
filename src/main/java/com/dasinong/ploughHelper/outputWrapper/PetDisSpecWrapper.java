package com.dasinong.ploughHelper.outputWrapper;

import com.dasinong.ploughHelper.model.PetDisSpec;

public class PetDisSpecWrapper {
	Long id;
	String pestName="";
	String alias="";
	String sympton="";
	String form="";
	String habbit="";
	String rule="";
	
	public PetDisSpecWrapper(PetDisSpec p){
		this.id = p.getPetDisSpecId();
		this.pestName = p.getPetDisSpecName();
		this.alias = p.getAlias();
		this.sympton = p.getSympthon();
		this.form = p.getForms();
		this.habbit = p.getHabits();
		this.rule = p.getRules();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPestName() {
		return pestName;
	}

	public void setPestName(String pestName) {
		this.pestName = pestName;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getSympton() {
		return sympton;
	}

	public void setSympton(String sympton) {
		this.sympton = sympton;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getHabbit() {
		return habbit;
	}

	public void setHabbit(String habbit) {
		this.habbit = habbit;
	}

	public String getRule() {
		return rule;
	}

	public void setRule(String rule) {
		this.rule = rule;
	}
	
	
}
