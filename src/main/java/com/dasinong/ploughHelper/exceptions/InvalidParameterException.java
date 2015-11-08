package com.dasinong.ploughHelper.exceptions;

public class InvalidParameterException extends Exception {
	
	private String paramName;
	private String paramType;

	public InvalidParameterException(String paramName, String paramType) {
		this.paramName = paramName;
		this.paramType = paramType;
	}
	
	public String getParamName() {
		return this.paramName;
	}
	
	public String getParamType() {
		return this.paramType;
	}
}
