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
import com.airshiplay.play.main.entity.MemberRankEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

@Entity
@Table(name = "obd_member_rank")
@Getter
@Setter
public class ObdMemberRankEntity extends MemberRankEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(length = 500)
	private String description;
	
	@OneToMany(mappedBy = "obdrank")
	private Set<ObdUserEntity> obdusers;



//	public void setUsers(Set<ObdUserEntity> users) {
//		this.users = users;
//	}
	
	
	
}
