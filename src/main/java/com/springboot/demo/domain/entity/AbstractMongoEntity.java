package com.springboot.demo.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

public abstract class AbstractMongoEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5962323677246312637L;
	
	@Id
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
