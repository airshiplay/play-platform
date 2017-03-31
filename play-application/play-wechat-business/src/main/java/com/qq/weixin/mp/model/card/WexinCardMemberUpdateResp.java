package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WexinCardMemberUpdateResp {
	private int errcode;
	private String errmsg;
	private boolean send_check;
}
