package com.dasinong.ploughHelper.exceptions;

public class ParameterMissingException extends RuntimeException {

	private String paramName;
	
	public ParameterMissingException(String name) {
		this.paramName = name;
	}
	
	public String getParamName() {
		return this.paramName;
	}
}
