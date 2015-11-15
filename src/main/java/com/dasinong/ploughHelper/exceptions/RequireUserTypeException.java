package com.dasinong.ploughHelper.exceptions;

public class RequireUserTypeException extends Exception {

	private String userType;
	
	public RequireUserTypeException(String userType) {
		this.userType = userType;
	}
	
	public String getUserType() {
		return this.userType;
	}
}
