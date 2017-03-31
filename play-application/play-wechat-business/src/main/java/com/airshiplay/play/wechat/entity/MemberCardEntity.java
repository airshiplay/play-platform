package com.airshiplay.play.wechat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 会员卡
 * 
 * @author lig
 *
 */
@Getter
@Setter
@Entity
@Table(name = "wechat_member_card")
public class MemberCardEntity extends DataEntity<AdminUserEntity, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 卡券
	 */
	@ManyToOne
	private CardCouponEntity cardCoupon;
	/**
	 * 会员
	 */
	@OneToOne
	@JoinColumn(name = "user_id")
	private WechatMemberEntity memberUser;
	/**
	 * 卡券code
	 */
	@NotNull
	@Column
	private String code;
	/**
	 * 会员卡编码
	 */
	@NotNull
	@Column
	private String membership_number;
	/**
	 * 卡券ID
	 */
	@Column
	@NotNull
	private String cardId;
}
