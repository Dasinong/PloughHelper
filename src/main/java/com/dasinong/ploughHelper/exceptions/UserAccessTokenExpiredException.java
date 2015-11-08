package com.dasinong.ploughHelper.exceptions;

public class UserAccessTokenExpiredException extends Exception {

	private String token;
	
	public UserAccessTokenExpiredException(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}
}
