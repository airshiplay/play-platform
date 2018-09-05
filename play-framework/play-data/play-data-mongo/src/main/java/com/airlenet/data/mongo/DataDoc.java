package com.airlenet.data.mongo;

import org.springframework.data.domain.Auditable;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

public class DataDoc<U> extends BaseDoc implements Auditable<U, String,LocalDateTime> {

	private static final long serialVersionUID = -6114318783615243294L;

	@DBRef(lazy = true)
	private U createdBy;

	private Date createdDate;

	@DBRef(lazy = true)
	private U lastModifiedBy;

	private Date lastModifiedDate;

	@Override
	public Optional<U> getCreatedBy() {
		return Optional.ofNullable(createdBy);
	}

	@Override
	public void setCreatedBy(final U createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public Optional<LocalDateTime> getCreatedDate() {
		return null == createdDate ? Optional.empty() : Optional.ofNullable(createdDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	}

	@Override
	public void setCreatedDate(final LocalDateTime createdDate) {
		this.createdDate = null == createdDate ? null : Date.from(createdDate.atZone(ZoneId.systemDefault()).toInstant());
	}

	@Override
	public Optional<U> getLastModifiedBy() {
		return Optional.ofNullable(lastModifiedBy);
	}

	@Override
	public void setLastModifiedBy(final U lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	public Optional<LocalDateTime> getLastModifiedDate() {
		return null == lastModifiedDate ? Optional.empty() : Optional.ofNullable(lastModifiedDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
	}

	@Override
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = null == lastModifiedDate ? null : Date.from(lastModifiedDate.atZone(ZoneId.systemDefault()).toInstant());
	}
}
