package com.springboot.demo.entity;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the User")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5885111588950855229L;
	@ApiModelProperty(value = "Unique ID")
	private Long id;
	@ApiModelProperty(value = "The User name")
	private String name;
	private List<Attribute> attributes;

	public User() {
		super();
	}
	
	public User(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}

}
