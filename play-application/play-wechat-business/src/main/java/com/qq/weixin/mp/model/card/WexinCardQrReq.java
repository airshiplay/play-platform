package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WexinCardQrReq {
	private String action_name;
	private Integer expire_seconds;
	private ActionInfo action_info;

	@Getter
	@Setter
	public static class ActionInfo {
		private Card card;
	}

	@Getter
	@Setter
	public static class Card {
		private String card_id;
		private String code;
		private String openid;
		private String is_unique_code;
		private String outer_str;
	}
}
