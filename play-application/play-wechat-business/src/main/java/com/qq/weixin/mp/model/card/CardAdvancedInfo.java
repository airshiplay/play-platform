package com.qq.weixin.mp.model.card;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardAdvancedInfo {
	List<TimeLimit> time_limit;
	List<TextImage> text_image_list;
	List<String> business_service;
	List<String> consume_share_card_list;
	boolean share_friends;
	@SerializedName("abstract")
	private AbstractCls abstractCls;

	@Getter
	@Setter
	public static class UseCondition {
		private String accept_category;
		private String reject_category;
		private boolean can_use_with_other_discount;
	}

	@Getter
	@Setter
	public static class AbstractCls {
		@SerializedName("abstract")
		private String abstractStr;
		private List<String> icon_url_list;
	}

	@Getter
	@Setter
	public static class TextImage {
		private String image_url;
		private String text;
	}

	@Getter
	@Setter
	public static class TimeLimit {
		private String type;
		private Integer begin_hour;
		private Integer end_hour;
		private Integer begin_minute;
		private Integer end_minute;
	}
}