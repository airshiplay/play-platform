package com.airshiplay.play.obd.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 车辆
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "obd_car")
@Getter
@Setter
public class CarEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 品牌
	 */
	@NotNull
	@Size(min = 2, max = 25)
	@Column(nullable = false, length = 50)
	private String brand;

	/**
	 * 型号
	 */
	@NotNull
	@Size(min = 2, max = 25)
	@Column(nullable = false, length = 50)
	private String model;

	/**
	 * 车牌号
	 */
	@NotNull
	@Size(min = 2, max = 25)
	@Column(nullable = false, length = 50)
	private String licenseNo;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private ObdUserEntity user;

	@OneToOne(fetch = FetchType.LAZY)
	private ObdDeviceEntity obdDevice;

	@OneToMany(mappedBy = "car")
	private Set<CarAlarmEntity> alarms;
}
