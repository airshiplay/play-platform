package com.airlenet.data.jpa;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
public class DataEntity<U, I extends Serializable> extends BaseEntity<I>implements Auditable<U, I,LocalDateTime> {

	private static final long serialVersionUID = 8855403157928861981L;

	@ManyToOne(fetch = FetchType.LAZY)
	private U createdBy;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private U lastModifiedBy;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
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
