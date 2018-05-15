package com.springboot.demo.entity;

import java.io.Serializable;

public class Attribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7581779002779038526L;

	private String key;
	private Object value;
	
	public Attribute() {
		super();
	}

	public Attribute(String key, Object value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	@Override
	public String toString() {
		
		System.err.println("*********************");
		
		return "Attribute [key=" + key + ", value=" + value + "]";
	}
}
