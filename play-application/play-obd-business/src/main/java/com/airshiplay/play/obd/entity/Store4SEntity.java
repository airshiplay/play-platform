package com.airshiplay.play.obd.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.main.entity.AreaEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

import java.util.List;

/**
 * 4S店
 * 
 * @author lig
 *
 */
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
	 * logo
	 * 
	 * formtype image
	 */
	@Column(length = 500)
	private String logo;
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


	@OneToMany(mappedBy = "store4S")
	private List<Store4SServiceEntity> store4SServices;
}
