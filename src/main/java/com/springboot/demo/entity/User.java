package com.springboot.demo.entity;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "用户对象", description = "用户对象POJO类，被xxx广泛使用")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5885111588950855229L;
	@ApiModelProperty(value = "用户唯一ID", allowEmptyValue = false)
	private Long id;
	@ApiModelProperty(value = "用户名字", allowEmptyValue = true)
	private String name;
	@ApiModelProperty(value = "用户属性列表", allowEmptyValue = true)
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", attributes=" + attributes + "]";
	}

}
