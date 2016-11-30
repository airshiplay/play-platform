package com.qq.weixin.mp.model.msg;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Persister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qq.weixin.WexinTool;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

public class WexinMsgFactory {
	static Logger logger = LoggerFactory.getLogger(WexinMsgFactory.class);
	static Persister serializer = new org.simpleframework.xml.core.Persister();
	public static String TOKEN = "3909DFE06AB249411392890C525BE8E1";
	public static String encodingAesKey = "SQJbl1ca0nym6GAdLXwsjSTcY07eKKaUR1qIjqjZGle";
	public static String appId = "wxf2b3c98bce38f6a8";
	static WXBizMsgCrypt wxCrypt;

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
	public static WechatMsgParent parse(String data, String timeStamp,
			String nonce, String encrypt_type, String msgSignature) {

		try {
			WechatMsgParent msgParent = serializer.read(WechatMsgParent.class,
					data, false);
			if (msgParent.getEncrypt() == null) {
				if (msgParent.getMsgType().equals("text")) {
					return serializer.read(WexinTextMsg.class, data, false);
				} else if (msgParent.getMsgType().equals("image")) {
					return serializer.read(WexinImageMsg.class, data, false);
				} else if (msgParent.getMsgType().equals("event")
						&& msgParent.getEvent().equals("Event")) {
					return serializer
							.read(WexinLocationMsg.class, data, false);
				}
			} else if ("aes".equals(encrypt_type)) {
				if (wxCrypt == null) {
					wxCrypt = new WXBizMsgCrypt(TOKEN, encodingAesKey, appId);
				}
				String a = wxCrypt.decryptMsg(msgSignature, timeStamp, nonce,
						data);
				return parse(a, timeStamp, nonce, encrypt_type, msgSignature);
			}

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
	public static String format(WechatMsgParent obj, String timeStamp,
			String nonce, String encrypt_type) {
		StringWriter out = new StringWriter();
		try {
			serializer.write(obj, out);
			if (encrypt_type == null || "".equals(encrypt_type))
				return out.toString();
		} catch (Exception e) {
			logger.error("WeChat", e);
		}
		try {// /////aes加密
			String replyMsg = wxCrypt.encryptMsg(out.toString(), timeStamp,
					nonce);
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
	public static String format(String xml, String timeStamp, String nonce,
			String encrypt_type) {
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
		private String timeStamp;
		@Element(name = "Nonce", required = false)
		private String nonce;
		@Element(name = "Event", required = false)
		private String event;

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

		public String getTimeStamp() {
			return timeStamp;
		}

		public void setTimeStamp(String timeStamp) {
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
	}
}
