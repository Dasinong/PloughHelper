package com.dasinong.ploughHelper.model;

import java.io.Serializable;

public class Field implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long fieldId;
	private String fieldName;
	private String other;
	
	public Long getFieldId() {
		return fieldId;
	}
	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
}
