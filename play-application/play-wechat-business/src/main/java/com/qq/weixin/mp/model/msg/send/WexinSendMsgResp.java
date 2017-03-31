package com.qq.weixin.mp.model.msg.send;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WexinSendMsgResp {
	private int errcode;
	private String errmsg;
	private String msg_id;
	private String msg_data_id;
}
