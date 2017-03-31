package com.qq.weixin.mp.model.card;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WexinMediaResp {
	private int errcode;
	private String errmsg;
	private String url;
}
