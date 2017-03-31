package com.qq.weixin.mp.model.msg.send;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class WexinSendMsg {
	protected String content;
	protected String media_id;
	protected String thumb_media_id;
	protected String title;
	protected String description;
	protected String card_id;

	public WexinSendMsg() {

	}
}