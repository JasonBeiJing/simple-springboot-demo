package com.springboot.demo.domain.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Log extends AbstractMongoDateTimeEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6686082797225704389L;

	@Indexed(background=true, unique=true)
	private String key;
	private Object value;
	private String type;
	private String name;
	private String desc;
	private Boolean xxx;
	
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
	public Boolean getXxx() {
		return xxx;
	}
	public void setXxx(Boolean xxx) {
		this.xxx = xxx;
	}
	@Override
	public String toString() {
		return "Log [key=" + key + ", value=" + value + ", type=" + type + ", name=" + name + ", desc=" + desc
				+ ", xxx=" + xxx + "]";
	}
	
}
