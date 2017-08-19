package com.airshiplay.play.repo.jpa;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
public class BaseEntity<I extends Serializable> extends AbstractPersistable<I> implements Persistable<I> {

	private static final long serialVersionUID = 2909347062854434851L;

//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(nullable = false, updatable = false)
//	private Date createdDate;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(nullable = false)
//	private Date lastModifiedDate;
//
//	@PrePersist
//	public void prePersist() {
//		this.createdDate = new Date();
//	}
//
//	@PreUpdate
//	public void preUpdate() {
//		this.lastModifiedDate = new Date();
//	}
//
//	public DateTime getCreatedDate() {
//		return null == createdDate ? null : new DateTime(createdDate);
//	}
//
//	public void setCreatedDate(final DateTime createdDate) {
//		this.createdDate = null == createdDate ? null : createdDate.toDate();
//	}
//
//	public DateTime getLastModifiedDate() {
//		return null == lastModifiedDate ? null : new DateTime(lastModifiedDate);
//	}
//
//	public void setLastModifiedDate(DateTime lastModifiedDate) {
//		this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDate();
//	}
}
