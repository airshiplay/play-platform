package com.qq.weixin.mp.model.msg.receive;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.qq.weixin.mp.model.msg.receive.WexinMsgFactory;

@Root(name = "xml")
public class WexinMsgText extends WexinMsgFactory.WechatMsgParent {

	@Element(name = "Content", data = true)
	private String content;

	public WexinMsgText() {
	}

	public WexinMsgText(String fromUserName, String toUserName) {
		super(toUserName, fromUserName);
		setMsgType("text");
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
