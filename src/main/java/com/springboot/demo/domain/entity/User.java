package com.springboot.demo.domain.entity;

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
	@ApiModelProperty(value = "用户手机号", allowEmptyValue = false)
	private String phone;
	@ApiModelProperty(value = "用户属性列表", allowEmptyValue = true)
	private List<Attribute> attributes;

	public User() {
		super();
	}
	
	public User(Long id, String name, String phone) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
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


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", attributes=" + attributes
				+ "]";
	}

}
