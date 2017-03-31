package com.qq.weixin.mp.model.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 会员卡
 * 
 * @author lig
 *
 */
@Getter
@Setter
@ToString
public class CardInfoMember {
	@Getter
	@Setter
	public static class BonusRule {
		private int cost_money_unit;
		private int increase_bonus;
		private int max_increase_bonus;
		private int init_increase_bonus;
		private int cost_bonus_unit;
		private int reduce_money;
		private int least_money_to_use_bonus;
		private int max_reduce_bonus;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	public static class CustomCell {
		private String name;
		private String tips;
		private String url;
	}

	@Getter
	@Setter
	@NoArgsConstructor
	public static class CustomField {
		private String name_type;
		private String name;
		private String url;

		public CustomField(String name_type, String url) {
			/**
			 * FIELD_NAME_TYPE_LEVEL 等级 FIELD_NAME_TYPE_COUPON 优惠券
			 * FIELD_NAME_TYPE_STAMP 印花 FIELD_NAME_TYPE_DISCOUNT 折扣
			 * FIELD_NAME_TYPE_ACHIEVEMEN 成就 FIELD_NAME_TYPE_MILEAGE 里程
			 * FIELD_NAME_TYPE_SET_POINTS 集点 FIELD_NAME_TYPE_TIMS 次数
			 */
			this.name_type = name_type;
			this.url = url;
		}

		public CustomField(String name_type, String name, String url) {
			/**
			 * FIELD_NAME_TYPE_LEVEL 等级 FIELD_NAME_TYPE_COUPON 优惠券
			 * FIELD_NAME_TYPE_STAMP 印花 FIELD_NAME_TYPE_DISCOUNT 折扣
			 * FIELD_NAME_TYPE_ACHIEVEMEN 成就 FIELD_NAME_TYPE_MILEAGE 里程
			 * FIELD_NAME_TYPE_SET_POINTS 集点 FIELD_NAME_TYPE_TIMS 次数
			 */
			this.name_type = name_type;
			this.name = name;
			this.url = url;
		}

	}

	private String background_pic_url;
	private CardBaseInfo base_info;
	private boolean supply_bonus;
	private boolean supply_balance;
	private String prerogative;
	private boolean auto_activate;
	private CardInfoMember.CustomField custom_field1;
	private CardInfoMember.CustomField custom_field2;
	private CardInfoMember.CustomField custom_field3;
	private String activate_url;
	private CardInfoMember.CustomCell custom_cell1;
	private CardInfoMember.CustomCell custom_cell2;
	private CardInfoMember.CustomCell custom_cell3;
	private CardInfoMember.BonusRule bonus_rule;
	private Integer discount;
	private CardAdvancedInfo advanced_info;
}