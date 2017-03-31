package com.qq.weixin.mp.model.msg.receive;

import java.io.StringWriter;
import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class WexinMsgFactory {
	static Logger logger = LoggerFactory.getLogger(WexinMsgFactory.class);
	static Persister serializer = new org.simpleframework.xml.core.Persister();

	// public static String TOKEN = "3909DFE06AB249411392890C525BE8E1";
	// public static String encodingAesKey =
	// "SQJbl1ca0nym6GAdLXwsjSTcY07eKKaUR1qIjqjZGle";
	// public static String appId = "wxf2b3c98bce38f6a8";
	// static WXBizMsgCrypt wxCrypt;

	/**
	 * 微信消息解码
	 * 
	 * @param data
	 * @param timeStamp
	 * @param nonce
	 * @param encrypt_type
	 * @param msgSignature
	 * @return
	 */
	public static WechatMsgParent parse(String data, String timeStamp, String nonce, String encrypt_type, String msgSignature, String token, String encodingAesKey, String appId) {

		try {
			WechatMsgParent msgParent = serializer.read(WechatMsgParent.class, data, false);
			if (msgParent.getEncrypt() == null) {
				if (msgParent.getMsgType().equals("text")) {
					return serializer.read(WexinMsgText.class, data, false);
				} else if (msgParent.getMsgType().equals("image")) {
					return serializer.read(WexinMsgMedia.class, data, false);
				} else if (msgParent.getMsgType().equals("voice")) {
					return serializer.read(WexinMsgMedia.class, data, false);
				} else if (msgParent.getMsgType().equals("video")) {
					return serializer.read(WexinMsgMedia.class, data, false);
				} else if (msgParent.getMsgType().equals("link")) {
					return serializer.read(WexinMsgMedia.class, data, false);
				}  else if (msgParent.getMsgType().equals("event") && msgParent.getEvent().equals("LOCATION")) {
					return serializer.read(WexinMsgEventLocation.class, data, false);
				} else if (msgParent.getMsgType().equals("event") && (msgParent.getEvent().equals("subscribe") || msgParent.getEvent().equals("unsubscribe"))) {
					return serializer.read(WexinMsgEventSubscribe.class, data, false);
				} else if (msgParent.getMsgType().equals("event") && (msgParent.getEvent().equals("card_pass_check") || msgParent.getEvent().equals("card_not_pass_check"))) {
					return serializer.read(WexinMsgEventCard.class, data, false);
				} else if (msgParent.getMsgType().equals("event") && msgParent.getEvent().equals("user_get_card")) {
					return serializer.read(WexinMsgEventCardUserGet.class, data, false);
				} else if (msgParent.getMsgType().equals("event") && msgParent.getEvent().equals("CLICK")) {
					return serializer.read(WexinMsgEventClick.class, data, false);
				} else if (msgParent.getMsgType().equals("event") && msgParent.getEvent().equals("VIEW")) {
					return serializer.read(WexinMsgEventView.class, data, false);
				}
			} else if ("aes".equals(encrypt_type)) {
				WXBizMsgCrypt wxCrypt = new WXBizMsgCrypt(token, encodingAesKey, appId);
				String a = wxCrypt.decryptMsg(msgSignature, timeStamp, nonce, data);
				return parse(a, timeStamp, nonce, encrypt_type, msgSignature, token, encodingAesKey, appId);
			}
			return msgParent;
		} catch (Exception e) {
			logger.error("WeChat", e);
		}
		return null;
	}

	/**
	 * 微信消息加码
	 * 
	 * @param obj
	 * @param timeStamp
	 * @param nonce
	 * @param encrypt_type
	 * @return
	 */
	public static String format(WechatMsgParent obj, String timeStamp, String nonce, String encrypt_type, String token, String encodingAesKey, String appId) {
		StringWriter out = new StringWriter();
		try {
			serializer.write(obj, out);
			if (encrypt_type == null || "".equals(encrypt_type))
				return out.toString();
		} catch (Exception e) {
			logger.error("WeChat", e);
		}
		try {// /////aes加密
			WXBizMsgCrypt wxCrypt = new WXBizMsgCrypt(token, encodingAesKey, appId);
			String replyMsg = wxCrypt.encryptMsg(out.toString(), timeStamp, nonce);
			return replyMsg;
		} catch (AesException e) {
			logger.error("WeChat", e);
		} catch (Exception e) {
			logger.error("WeChat", e);
		}
		return out.toString();
	}

	/**
	 * 微信消息加码
	 * 
	 * @param xml
	 * @param timeStamp
	 * @param nonce
	 * @param encrypt_type
	 * @return
	 */
	public static String format(String xml, String timeStamp, String nonce, String encrypt_type, WXBizMsgCrypt wxCrypt) {
		if (encrypt_type == null || "".equals(encrypt_type))
			return xml;
		try {
			String replyMsg = wxCrypt.encryptMsg(xml, timeStamp, nonce);
			return replyMsg;
		} catch (AesException e) {
			logger.error("WeChat", e);
		} catch (Exception e) {
			logger.error("WeChat", e);
		}
		return "";
	}

	@Root(name = "xml")
	public static class WechatMsgParent {
		@Element(name = "MsgType", data = true, required = false)
		private String msgType;
		@Element(name = "ToUserName", data = true)
		private String toUserName;
		@Element(name = "FromUserName", data = true, required = false)
		private String fromUserName;
		@Element(name = "CreateTime", required = false)
		private Long createTime;
		@Element(name = "MsgId", required = false)
		private String msgId;
		@Element(name = "Encrypt", required = false, data = true)
		private String encrypt;
		@Element(name = "TimeStamp", required = false)
		private Long timeStamp;
		@Element(name = "Nonce", required = false)
		private String nonce;
		@Element(name = "Event", required = false)
		private String event;

		public WechatMsgParent() {
		}

		public WechatMsgParent(String toUserName, String fromUserName) {
			this.toUserName = toUserName;
			this.fromUserName = fromUserName;
			this.createTime = new Date().getTime() / 1000;
		}

		public String getMsgType() {
			return msgType;
		}

		public void setMsgType(String msgType) {
			this.msgType = msgType;
		}

		public String getToUserName() {
			return toUserName;
		}

		public void setToUserName(String toUserName) {
			this.toUserName = toUserName;
		}

		public String getFromUserName() {
			return fromUserName;
		}

		public void setFromUserName(String fromUserName) {
			this.fromUserName = fromUserName;
		}

		public Long getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Long createTime) {
			this.createTime = createTime;
		}

		public String getMsgId() {
			return msgId;
		}

		public void setMsgId(String msgId) {
			this.msgId = msgId;
		}

		public String getEncrypt() {
			return encrypt;
		}

		public void setEncrypt(String encrypt) {
			this.encrypt = encrypt;
		}

		public Long getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(Long timeStamp) {
			this.timeStamp = timeStamp;
		}

		public String getNonce() {
			return this.nonce;
		}

		public void setNonce(String nonce) {
			this.nonce = nonce;
		}

		public String getEvent() {
			return event;
		}

		public void setEvent(String event) {
			this.event = event;
		}
		
		public String toXmlString(){
			StringWriter out = new StringWriter();
			try {
				serializer.write(this, out);
				return out.toString();
			} catch (Exception e) {
				logger.error("WeChat", e);
			}
			return "";
		}
	}

}
