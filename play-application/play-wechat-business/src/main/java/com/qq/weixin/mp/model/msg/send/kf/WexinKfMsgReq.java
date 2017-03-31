package com.qq.weixin.mp.model.msg.send.kf;

import com.qq.weixin.mp.model.msg.send.WexinSendMsg;

import lombok.Getter;
import lombok.Setter;

/**
 * 客服消息请求格式，不完善
 * 
 * @author lig
 *
 */
@Setter
@Getter
public class WexinKfMsgReq {
	private String touser;
	private String msgtype;

	private WexinSendMsg text;

	private WexinSendMsg image;

	private WexinSendMsg voice;
	private WexinSendMsg video;

	private WexinSendMsg wxcard;

	private WexinKefu customservice;

	public WexinKfMsgReq setKf(String kf_account) {
		this.customservice = new WexinKefu();
		this.customservice.setKf_account(kf_account);
		return this;
	}

	public static WexinKfMsgReq getTextMsg(String touser, String content) {
		WexinKfMsgReq req = new WexinKfMsgReq();
		req.touser = touser;
		req.setMsgtype("text");
		req.text = new WexinSendMsg();
		req.text.setContent(content);
		return req;
	}

	public static WexinKfMsgReq getImageMsg(String touser, String media_id) {
		WexinKfMsgReq req = new WexinKfMsgReq();
		req.touser = touser;
		req.setMsgtype("image");
		req.image = new WexinSendMsg();
		req.image.setMedia_id(media_id);
		return req;
	}

	public static WexinKfMsgReq getVoiceMsg(String touser, String media_id) {
		WexinKfMsgReq req = new WexinKfMsgReq();
		req.touser = touser;
		req.setMsgtype("voice");
		req.voice = new WexinSendMsg();
		req.voice.setMedia_id(media_id);
		return req;
	}

	public static WexinKfMsgReq getCardMsg(String touser, String card_id) {
		WexinKfMsgReq req = new WexinKfMsgReq();
		req.touser = touser;
		req.setMsgtype("wxcard");
		req.wxcard = new WexinSendMsg();
		req.wxcard.setCard_id(card_id);
		return req;
	}

	public static WexinKfMsgReq getVideoMsg(String touser, String media_id, String thumb_media_id, String title, String description) {
		WexinKfMsgReq req = new WexinKfMsgReq();
		req.touser = touser;
		req.setMsgtype("video");
		req.video = new WexinSendMsg();
		req.video.setMedia_id(media_id);
		req.video.setThumb_media_id(thumb_media_id);
		req.video.setTitle(title);
		req.video.setDescription(description);
		return req;
	}
}
