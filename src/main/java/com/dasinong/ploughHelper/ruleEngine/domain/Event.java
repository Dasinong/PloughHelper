package com.dasinong.ploughHelper.ruleEngine.domain;

public class Event {
	private EventType type;

	public Event(EventType type) {
		super();
		this.type = type;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}
	
	
}