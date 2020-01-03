package com.springboot.demo.domain.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class Attribute implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7581779002779038526L;

	@ApiModelProperty(value = "用户属性-KEY", allowEmptyValue = false)
	private String key;
	@ApiModelProperty(value = "用户属性值-VALUE", allowEmptyValue = false)
	private String value;
	
	public Attribute() {
		super();
	}

	public Attribute(String key, String value) {
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Attribute [key=" + key + ", value=" + value + "]";
	}
}
