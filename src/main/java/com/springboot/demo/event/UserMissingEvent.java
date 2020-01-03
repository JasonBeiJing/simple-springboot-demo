package com.springboot.demo.event;

import org.springframework.context.ApplicationEvent;

public class UserMissingEvent extends ApplicationEvent {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7980719041553301904L;

	private String userId;
	
	public UserMissingEvent(Object source, String userId) {
		super(source);
		this.setUserId(userId);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
