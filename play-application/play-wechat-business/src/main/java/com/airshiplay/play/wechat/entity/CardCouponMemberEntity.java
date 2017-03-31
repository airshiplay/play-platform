package com.airshiplay.play.wechat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 会员卡券
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "wechat_card_coupon_member")
@Getter
@Setter
public class CardCouponMemberEntity extends DataEntity<AdminUserEntity, Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 卡券
	 */
	@OneToOne
	@JoinColumn(name = "card_coupon_id")
	private CardCouponEntity cardCoupon;
	/**
	 * 商户名字
	 */
	@Column
	@Size(max = 12)
	@NotNull
	private String brandName;
	/**
	 * 卡券名
	 */
	@Column
	@Size(max = 9)
	@NotNull
	private String title;
	/**
	 * 卡券的商户logo
	 * 
	 * @formtype image
	 */
	@Column
	@NotNull
	private String logoUrl;
	/**
	 * 会员卡背景图 1000像素*600像素以下
	 */
	@Column
	private String backgroudUrl;
	
	@Column
	private String wxlogoUrl;
	@Column
	private String wxbackgroudUrl;
	/**
	 * 服务电话
	 */
	private String servicePhone;
	/**
	 * 卡券使用提醒
	 */
	@Column(length = 48)
	@NotNull
	@Size(max = 16)
	private String notice;
	/**
	 * 券颜色
	 */
	@Column
	@NotNull
	@Size(max = 20)
	private String color;

	/**
	 * 卡券使用说明
	 * 
	 * @formtype textarea
	 */
	@Column(length = 3072)
	@NotNull
	@Size(max = 1024)
	private String description;

	/**
	 * 会员卡数量
	 */
	@Column(name = "sku_quantity")
	@NotNull
	@Max(100000000)
	private Long skuQuantity;

	/**
	 * 会员卡特权说明
	 * 
	 * @formtype textarea
	 */
	@Column(name = "prerogative", length = 1024)
	@NotNull
	private String prerogative;
	/**
	 * 初始积分
	 */
	@Column(name = "init_increase_bonus")
	@NotNull
	private int init_increase_bonus;

	/**
	 * 消费金额(单位/分)
	 */
	@Column(name = "cost_money_unit")
	@NotNull
	private int cost_money_unit;
	/**
	 * 增加的积分
	 */
	@Column(name = "increase_bonus")
	@NotNull
	private int increase_bonus;
	/**
	 * 单次获取积分上限
	 */
	@Column(name = "max_increase_bonus")
	@NotNull
	private int max_increase_bonus;
	/**
	 * 每使用积分
	 */
	@Column(name = "cost_bonus_unit")
	@NotNull
	private int cost_bonus_unit;

	/**
	 * 抵扣xx元（单位/分)
	 */
	@Column(name = "reduce_money")
	@NotNull
	private int reduce_money;
	/**
	 * 抵扣条件，满xx元（单位/分）可用
	 */
	@Column(name = "least_money_to_use_bonus")
	@NotNull
	private int least_money_to_use_bonus;
	/**
	 * 抵扣条件，单笔最多使用xx积分
	 */
	@Column(name = "max_reduce_bonus")
	@NotNull
	private int max_reduce_bonus;
	/**
	 * 折扣，该会员卡享受的折扣优惠,填10就是九折。
	 */
	@Column(name = "discount")
	private Integer discount;
	/**
	 * 卡券ID
	 */
	@Column(name = "card_id")
	private String cardId;

	/**
	 * 激活地址
	 */
	@Column(name = "activate_url")
	private String activateUrl;

}
