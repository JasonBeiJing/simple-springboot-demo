package com.springboot.demo.domain.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("C_Log")
public class Log extends AbstractMongoDateTimeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6686082797225704389L;

	// @Indexed(background=true, unique=true)
	// spring.data.mongodb.auto-index-creation=true
	private String key;
	private String type;
	private String name;
	private String desc;
	private Object value;
	
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
