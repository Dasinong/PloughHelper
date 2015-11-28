package com.dasinong.ploughHelper.exceptions;

public class SmsProviderException extends Exception {

	private int code;
	private String message;
	
	public SmsProviderException(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return this.code;
	}
	
	public String getMessage() {
		return this.message;
	}
}
