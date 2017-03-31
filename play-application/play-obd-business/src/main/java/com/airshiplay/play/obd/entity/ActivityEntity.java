package com.airshiplay.play.obd.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.springframework.format.annotation.DateTimeFormat;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 活动推广
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "obd_activity")
@Getter
@Setter
public class ActivityEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column
	private String content;

	@ManyToMany
	private Set<Store4SEntity> store4Ss;

	/** 起始日期 */
	@DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm")
	private Date beginDate;

	/** 结束日期 */
	@DateTimeFormat(pattern = "yyyy年MM月dd日 HH:mm")
	private Date endDate;
}
