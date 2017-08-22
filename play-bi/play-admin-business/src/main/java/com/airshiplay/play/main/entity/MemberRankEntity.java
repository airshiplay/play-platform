package com.airshiplay.play.main.entity;

import com.airlenet.play.repo.jpa.DataEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Table(name = "business_member_rank")
@Entity
public class MemberRankEntity extends DataEntity<AdminUserEntity, Long> {

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
	
	@Column(length=500)
	private String description;
	
	@OneToMany(mappedBy = "rank")
	private Set<MemberUserEntity> members;

}
