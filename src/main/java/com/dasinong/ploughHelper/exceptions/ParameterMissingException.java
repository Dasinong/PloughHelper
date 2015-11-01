package com.dasinong.ploughHelper.exceptions;

public class ParameterMissingException extends RuntimeException {

	private String paramName = null;
	
	public ParameterMissingException() {}
	
	public ParameterMissingException(String name) {
		this.paramName = name;
	}
	
	public String getParamName() {
		return this.paramName;
	}
}
