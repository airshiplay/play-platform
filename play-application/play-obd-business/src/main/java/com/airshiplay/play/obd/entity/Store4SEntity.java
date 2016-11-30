package com.airshiplay.play.obd.entity;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.entity.AreaEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

@Entity
@Table(name = "obd_4store")
@Getter
@Setter
public class Store4SEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 2, max = 50)
	@Column(nullable = true, length = 200)
	private String name;

	@Column(length = 200)
	private String phone;

	@ManyToOne(fetch = FetchType.LAZY)
	private AreaEntity area;

	@Column(length = 200)
	private String address;
	/**
	 * 经度
	 */
	@Column(length = 200)
	private String lng;
	/**
	 * 纬度
	 */
	@Column(length = 200)
	private String lat;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "primary_leader_id")
	private AdminUserEntity primaryLeader;
}
