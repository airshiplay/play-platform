package com.qq.weixin.mp.model.msg;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.qq.weixin.mp.model.msg.WexinMsgFactory;

@Root(name = "xml")
public class WexinImageMsg extends WexinMsgFactory.WechatMsgParent {

	@Element(name = "PicUrl", data = true)
	private String picUrl;
	@Element(name = "MediaId", data = true)
	private String mediaId;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
