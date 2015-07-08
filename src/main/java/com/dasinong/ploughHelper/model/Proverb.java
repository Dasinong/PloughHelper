package com.dasinong.ploughHelper.model;

import java.io.Serializable;

public class Proverb implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private Long proverbId;
	private String necessaryCondition;
	private String content = "";
	
	public Proverb() {
	}
	
	public Proverb(String content) {
		this.content = content;
	}

	public Long getProverbId() {
		return proverbId;
	}

	public void setProverbId(Long proverbId) {
		this.proverbId = proverbId;
	}

	public String getNecessaryCondition() {
		return necessaryCondition;
	}

	public void setNecessaryCondition(String necessaryCondition) {
		this.necessaryCondition = necessaryCondition;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
