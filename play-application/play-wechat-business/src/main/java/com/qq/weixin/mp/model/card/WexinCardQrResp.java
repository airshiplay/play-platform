package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WexinCardQrResp {

	private int errcode;
	private String errmsg;
	private String ticket;
	private int expire_seconds;
	private String url;
	private String show_qrcode_url;

}
