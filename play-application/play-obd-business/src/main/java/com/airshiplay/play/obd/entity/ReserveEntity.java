package com.airshiplay.play.obd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.DateTime;

import com.airshiplay.play.repo.jpa.BaseEntity;

/**
 * 预约
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "obd_reserve")
public class ReserveEntity extends BaseEntity<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, updatable = false)
	private Date createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date lastModifiedDate;

	@PrePersist
	public void prePersist() {
		this.createdDate = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		this.lastModifiedDate = new Date();
	}

	public DateTime getCreatedDate() {
		return null == createdDate ? null : new DateTime(createdDate);
	}

	public void setCreatedDate(final DateTime createdDate) {
		this.createdDate = null == createdDate ? null : createdDate.toDate();
	}

	public DateTime getLastModifiedDate() {
		return null == lastModifiedDate ? null : new DateTime(lastModifiedDate);
	}

	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = null == lastModifiedDate ? null : lastModifiedDate.toDate();
	}
}
