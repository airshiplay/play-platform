package com.airshiplay.play.obd.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 订单
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "obd_order")
@Getter
@Setter
public class ObdOrderEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToMany(mappedBy = "orders", fetch = FetchType.LAZY)
	private Set<ObdUserEntity> users = new HashSet<>();
}
