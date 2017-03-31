package com.airshiplay.play.obd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 车辆告警
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "obd_vehicle_alarm")
@Getter
@Setter
public class CarAlarmEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@Enumerated(EnumType.STRING)
	private AlarmType type;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status;

	@Column
	private String description;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "obd_id")
	private ObdDeviceEntity obdDevice;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "car_id")
	private CarEntity car;

	public static enum Status {
		init, processing, solved, ignore
	}

	public static enum AlarmType {
		Error1("故障状态灯异常"), Error2("行驶里程超出阈值");

		private String desc;

		AlarmType(String desc) {
			this.setDesc(desc);
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}
	}
}
