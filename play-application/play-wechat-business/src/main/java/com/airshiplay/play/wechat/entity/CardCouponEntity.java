package com.airshiplay.play.wechat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 卡券管理
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "wechat_card_coupon")
@Getter
@Setter
// @MappedSuperclass
public class CardCouponEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 会员卡券
	 */
	@OneToOne(mappedBy = "cardCoupon")
	private CardCouponMemberEntity memberCard;
	/**
	 * 卡券类型
	 */
	@Column(name = "card_type")
	private CardCouponType cardType;

	@Column(name = "card_id", unique = true)
	private String cardId;

	/**
	 * 卡券名
	 */
	@Column
	private String title;
	/**
	 * 卡券余量
	 */
	@Column
	private Long quantity;
	/**
	 * 卡券状态
	 */
	@Column
	private String status;
}
