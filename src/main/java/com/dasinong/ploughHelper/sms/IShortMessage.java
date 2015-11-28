package com.dasinong.ploughHelper.sms;

public interface IShortMessage {
	
	public ShortMessageType getType();
	
	public String getContent();
	
	public String getSmsProductId();
}
