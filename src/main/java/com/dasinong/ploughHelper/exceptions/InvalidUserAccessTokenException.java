package com.dasinong.ploughHelper.exceptions;

public class InvalidUserAccessTokenException extends Exception {

	private String token;
	
	public InvalidUserAccessTokenException(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return this.token;
	}
}
