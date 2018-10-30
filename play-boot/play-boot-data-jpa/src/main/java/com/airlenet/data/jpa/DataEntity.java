package com.airlenet.data.jpa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.airlenet.data.domain.Userable;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
public class DataEntity<U extends Userable, I extends Serializable> extends BaseEntity<I>implements Auditable<U, I,LocalDateTime> {

	private static final long serialVersionUID = 8855403157928861981L;

	@ManyToOne(fetch = FetchType.LAZY)
	private U createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createdDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private U lastModifiedBy;

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
		return createdDate ==null?Optional.empty():Optional.of(LocalDateTime.ofInstant(createdDate.toInstant(),ZoneId.systemDefault()));
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
		return lastModifiedDate ==null?Optional.empty():Optional.of(LocalDateTime.ofInstant(lastModifiedDate.toInstant(),ZoneId.systemDefault()));
	}

	@Override
	public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
		this.lastModifiedDate = null == lastModifiedDate ? null : Date.from(lastModifiedDate.atZone(ZoneId.systemDefault()).toInstant());
	}

}
