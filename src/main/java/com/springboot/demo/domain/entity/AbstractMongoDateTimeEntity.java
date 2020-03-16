package com.springboot.demo.domain.entity;

import java.time.LocalDateTime;

public abstract class AbstractMongoDateTimeEntity extends AbstractMongoEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8833560153585950910L;

	private LocalDateTime created;
	private String createdBy;
	private String deleted;
	private LocalDateTime deletedBy;

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
	}

	public LocalDateTime getDeletedBy() {
		return deletedBy;
	}

	public void setDeletedBy(LocalDateTime deletedBy) {
		this.deletedBy = deletedBy;
	}
}
