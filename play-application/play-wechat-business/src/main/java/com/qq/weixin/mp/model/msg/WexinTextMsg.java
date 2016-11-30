package com.qq.weixin.mp.model.msg;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.qq.weixin.mp.model.msg.WexinMsgFactory;

@Root(name = "xml")
public class WexinTextMsg extends WexinMsgFactory.WechatMsgParent {
	
	
	@Element(name = "Content", data = true)
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
