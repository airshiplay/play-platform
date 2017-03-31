package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WexinCardUpdateUserReq {
	private String code;
	private String card_id;
	private String background_pic_url;
	private String record_bonus;
	private int bonus;
	private String custom_field_value1;
	private NotifyOptional notify_optional;

	@Getter
	@Setter
	public static class NotifyOptional {
		private boolean is_notify_bonus;
		private boolean is_notify_balance;
		private boolean is_notify_custom_field1;

	}
}
