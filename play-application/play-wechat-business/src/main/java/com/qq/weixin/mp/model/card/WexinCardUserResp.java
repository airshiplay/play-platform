package com.qq.weixin.mp.model.card;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WexinCardUserResp {
	private int errcode;
	private String errmsg;
	private String openid;
	private String nickname;
	private String membership_number;
	private int bonus;
	private String sex;
	private String user_card_status;
	private UserInfo user_info;
	private boolean has_active;

	@Getter
	@Setter
	public static class UserInfo {
		private List<NameValue> common_field_list;
		private List<String> custom_field_list;
	}

	@Getter
	@Setter
	public static class NameValue {
		private String name;
		private String value;
	}
}
