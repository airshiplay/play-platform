package com.airshiplay.play.weixin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.api.LogService;
import com.airshiplay.play.wechat.entity.WechatConfigEntity;
import com.airshiplay.play.wechat.service.WechatConfigEntityService;
import com.airshiplay.play.wechat.service.WeixinMsgService;
import com.qq.weixin.WexinTool;
import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.model.msg.receive.WexinMsgFactory;
import com.qq.weixin.mp.model.msg.receive.WexinMsgFactory.WechatMsgParent;
import com.querydsl.jpa.impl.JPAQuery;

@Controller
@RequestMapping(value = "/weixin")
public class WexinAccessController {
	private static final Logger logger = LoggerFactory.getLogger(WexinAccessController.class);

	@Autowired
	private WechatConfigEntityService wechatConfigEntityService;

	@Autowired
	private WeixinMsgService wexinMsgService;

	@Autowired
	private LogService logService;

	private static Map<Long, WechatConfigEntity> configMap = new HashMap<Long, WechatConfigEntity>();

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Object echoGet(@PathVariable Long id, @RequestParam(required = false) String signature, @RequestParam(required = false) String echostr, @RequestParam(required = false) String timestamp,
			@RequestParam(required = false) String nonce) {
		WechatConfigEntity config = null;
		if (!configMap.containsKey(id)) {
			config = wechatConfigEntityService.getOne(id);
			configMap.put(id, config);
		} else {
			config = configMap.get(id);
		}
		logger.info("signature={},timestamp={},nonce={},token={}", signature, timestamp, nonce, config.getToken());
		if (echostr != null && WexinTool.checkSignature(signature, timestamp, nonce, config.getToken())) {
			logger.info("消息接入验证成功");
			return echostr;
		}
		return null;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = { "text/xml;charset=iso-8859-1" }, produces = "text/xml;charset=UTF-8")
	@ResponseBody
	public Object echoPost(@PathVariable Long id, @RequestParam(required = false, name = "signature") String signature, @RequestParam(required = false, name = "openid") String openid,
			@RequestParam(required = false, name = "timestamp") String timeStamp, @RequestParam(required = false, name = "nonce") String nonce,
			@RequestParam(required = false, name = "msg_signature") String msg_signature, @RequestParam(required = false, name = "encrypt_type") String encrypt_type, @RequestBody String body,
			HttpServletRequest request) throws AesException {
		WechatConfigEntity config = null;
		if (!configMap.containsKey(id)) {
			config = wechatConfigEntityService.getOne(id);
			configMap.put(id, config);
		} else {
			config = configMap.get(id);
		}
		boolean pass = WexinTool.checkSignature(signature, timeStamp, nonce, config.getToken());
		logger.info("\nWeChat signature={}\n messge req\n{}", pass, body);
		if (pass) {
			String echo = null;
			WechatMsgParent msg = WexinMsgFactory.parse(body, timeStamp, nonce, encrypt_type, msg_signature, config.getToken(), config.getEncodingAesKey(), config.getAppId());
			WechatMsgParent result =wexinMsgService.handlerMessage(config,msg,body);
			 
			if (result != null) {
				echo = WexinMsgFactory.format(result, timeStamp, nonce, encrypt_type, config.getToken(), config.getEncodingAesKey(), config.getAppId());
			}
			logger.info("\nWeChat messge reply\n{}", echo);
			return echo;
		}
		return "";
	}

}
