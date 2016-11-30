package com.airshiplay.play.weixin.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.qq.weixin.WexinHttp;
import com.qq.weixin.WexinTool;
import com.qq.weixin.mp.api.WexinSmartApi;
import com.qq.weixin.mp.model.msg.WexinMsgFactory;
import com.qq.weixin.mp.model.msg.WexinTextMsg;
import com.qq.weixin.mp.model.msg.WexinMsgFactory.WechatMsgParent;
import com.qq.weixin.mp.model.smart.WexinSemproxyReq;

@RestController
@RequestMapping(value = "/weixin")
public class WexinAccessController {
	private static final Logger logger = LoggerFactory
			.getLogger(WexinAccessController.class);

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Object echoGet(@RequestParam(required = false) String signature,
			@RequestParam(required = false) String echostr,
			@RequestParam(required = false) String timestamp,
			@RequestParam(required = false) String nonce) {
		if (echostr != null
				&& WexinTool.checkSignature(signature, timestamp, nonce,
						WexinMsgFactory.TOKEN)) {
			logger.info("消息接入验证成功");
			return echostr;
		}
		return null;
	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = { "text/xml;charset=iso-8859-1" }, produces = "text/xml;charset=UTF-8")
	public Object echoPost(
			@RequestParam(required = false, name = "signature") String signature,
			@RequestParam(required = false, name = "openid") String openid,
			@RequestParam(required = false, name = "timestamp") String timeStamp,
			@RequestParam(required = false, name = "nonce") String nonce,
			@RequestParam(required = false, name = "msg_signature") String msg_signature,
			@RequestParam(required = false, name = "encrypt_type") String encrypt_type,
			@RequestBody String body, HttpServletRequest request) {
		String echo = "<xml><ToUserName><![CDATA[oTl2euLs6iswJU5kt2H4FtLZAiZk]]></ToUserName>"
				+ "<FromUserName><![CDATA[gh_06e069f27b90]]></FromUserName>"
				+ " <CreateTime>1471937057</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>"
				+ "<Content><![CDATA[您发送的消息已收到，等待反馈]]></Content>"
				+ "<MsgId>6321921522201401279</MsgId>" + "</xml>";

		logger.info("\nWeChat messge req\n{}",body);
		if (WexinTool.checkSignature(signature, timeStamp, nonce,
				WexinMsgFactory.TOKEN)) {
			WechatMsgParent msg = WexinMsgFactory.parse(body, timeStamp,
					nonce, encrypt_type, msg_signature);
			if (msg instanceof WexinTextMsg) {
				WexinTextMsg textmsg = (WexinTextMsg) msg;
				String content = textmsg.getContent();

				WexinSmartApi smartApi = WexinHttp.getApi(
						WexinSmartApi.baseUrl, WexinSmartApi.class);
				WexinSemproxyReq req = new WexinSemproxyReq();
				req.setAppid(WexinHttp.getDefaultWechatConfig().getAppid());
				req.setQuery(textmsg.getContent());
				req.setUid(textmsg.getFromUserName());
				req.setCategory("weather");
				req.setCity("北京");

				try {
					Map<String, Object> result = smartApi.semproxySearch(req)
							.execute().body();
					content = result.toString();
				} catch (IOException e) {
					 
					e.printStackTrace();
				}
				WexinTextMsg result = new WexinTextMsg();
				result.setContent(content);
				result.setCreateTime(new Date().getTime() / 1000);
				result.setFromUserName(textmsg.getToUserName());
				result.setMsgType("text");
				result.setToUserName(textmsg.getFromUserName());
				echo = WexinMsgFactory.format(result, timeStamp, nonce,
						encrypt_type);
				
			}
			logger.info("\nWeChat messge reply\n{}",echo);
			return echo;
		}

		return WexinMsgFactory.format(echo, timeStamp, nonce, encrypt_type);
	}

}
