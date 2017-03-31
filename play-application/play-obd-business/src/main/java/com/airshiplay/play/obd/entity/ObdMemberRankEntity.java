package com.airshiplay.play.obd.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 会员等级
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "obd_member_rank")
@Getter
@Setter
public class ObdMemberRankEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Size(min = 1, max = 50)
	@Column(nullable = false, length = 200)
	private String name;

	@Size(min = 1, max = 50)
	@Column(nullable = false, length = 200)
	private String code;

	@Column(length = 500)
	private String icon;

	@Column(length = 500)
	private String description;

	@OneToMany(mappedBy = "obdrank")
	private Set<ObdUserEntity> obdusers;

	// public void setUsers(Set<ObdUserEntity> users) {
	// this.users = users;
	// }

}
