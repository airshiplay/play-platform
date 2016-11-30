package com.airshiplay.play.obd.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.core.helper.Patterns;
import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

@Entity
@Table(name = "obd_device")
@Getter
@Setter
public class ObdDeviceEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 设备编号
	 */
	@NotNull
	@Size(min = 1, max = 64)
	@Column(unique = true, length = 64)
	@Pattern(regexp = Patterns.REGEX_LETTER_NUMBER)
	private String deviveNo;
	/**
	 * 型号
	 */
	@NotNull
	@Size(min = 1, max = 64)
	@Column(unique = true, length = 64)
	private String type;
	/**
	 * 描述
	 */
	@Size(min = 1, max = 100)
	@Column(unique = true, length = 200)
	private String description;

	@OneToOne(mappedBy = "obdDevice")
	private VehicleEntity car;

	@OneToMany(mappedBy = "obdDevice")
	private Set<VehicleAlarmEntity> alarm;
}
