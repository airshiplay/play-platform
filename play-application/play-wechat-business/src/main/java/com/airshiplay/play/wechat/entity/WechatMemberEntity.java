package com.airshiplay.play.wechat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.MemberUserEntity;

/**
 * 微信会员
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "wechat_member")
@Getter
@Setter
public class WechatMemberEntity extends MemberUserEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@OneToOne(mappedBy = "memberUser")
	private MemberCardEntity memberCard;

	@Column
	@NotNull
	private String openid;
}
