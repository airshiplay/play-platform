package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WexinCardUpdateUserResp {
	private int errcode;
	private String errmsg;
	private int result_bonus;
	private int result_balance;
	private String openid;
}
