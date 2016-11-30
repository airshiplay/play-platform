package com.airshiplay.play.obd.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.MemberUserEntity;

@Getter
@Setter
@Entity
@Table(name = "obd_user")
public class ObdUserEntity extends MemberUserEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy = "user")
	private Set<VehicleEntity> cars;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "obd_user_to_order")
	private Set<OrderEntity> orders = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "obdrank_id")
	private ObdMemberRankEntity obdrank;
}
