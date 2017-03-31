package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardInfo {
	/**
	 * 团购券：GROUPON; 折扣券：DISCOUNT; 礼品券：GIFT; 代金券：CASH; 通用券：GENERAL_COUPON;
	 * 会员卡：MEMBER_CARD; 景点门票：SCENIC_TICKET； 电影票：MOVIE_TICKET； 飞机票：BOARDING_PASS；
	 * 会议门票：MEETING_TICKET； 汽车票：BUS_TICKET;
	 */
	private String card_type;
	/**
	 * 会员卡:MEMBER_CARD
	 */
	private CardInfoMember member_card;
	/**
	 * 折扣券：DISCOUNT
	 */
	private CardInfoDiscount discount;
	/**
	 * 团购券：GROUPON
	 */
	private CardInfoGroupon groupon;
	/**
	 * 代金券：CASH
	 */
	private CardInfoCash cash;
}