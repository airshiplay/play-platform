package com.airshiplay.play.wechat.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airshiplay.play.main.api.LogService;
import com.airshiplay.play.main.api.LogService.LogLevel;
import com.airshiplay.play.main.api.LogService.OperateType;
import com.airshiplay.play.wechat.entity.WechatConfigEntity;
import com.airshiplay.play.wechat.service.WechatConfigEntityService;
import com.qq.weixin.WexinHttp;
import com.qq.weixin.WexinTool;
import com.qq.weixin.mp.api.WexinApiBasic;
import com.qq.weixin.mp.model.WexinTicket;

/**
 * 微信JS-SDK Demo
 * 
 * @author lig
 *
 */
@Controller
@RequestMapping("/wechat/jsdkdemo")
public class WexinJsSdkDemoController {
	private static final Logger logger =LoggerFactory.getLogger(WexinJsSdkDemoController.class);
	private static WexinTicket jsTicket = null;
	@Autowired
	private WechatConfigEntityService wechatConfigEntityService;

	@Autowired
	LogService logService;

	public WexinTicket getWexinTicket() {
		if (jsTicket == null || jsTicket.isExpires()) {
			try {
				WechatConfigEntity config = wechatConfigEntityService.findOne(1L);
				WexinApiBasic api = WexinHttp.getApi(config.getAppId(), config.getAppSecret(), WexinApiBasic.class);
				WexinTicket ticket = api.getTicket("jsapi").execute().body();
				if (ticket.getErrcode() != 0) {
					logService.addLog(OperateType.OTHER, LogLevel.ERROR, "微信JS Ticket错误" + ticket);
				} else {
					jsTicket = ticket;
				}
			} catch (IOException e) {
				logService.addLog(OperateType.OTHER, LogLevel.ERROR, "微信JS Ticket错误", e);
			}
		}
		return jsTicket;
	}

	@ModelAttribute
	public void init(Model model, HttpServletRequest request) {
		logger.info(request.getRequestURL().toString());
		WechatConfigEntity config = wechatConfigEntityService.findOne(1L);
		 
		WexinTicket jsTicket = getWexinTicket();
		Map<String, String> map = WexinTool.jsApiSign(jsTicket.getTicket(), request.getRequestURL().toString());
		model.addAttribute("config", config);
		model.addAttribute("hashMap", map);
	}

	@RequestMapping(value = { "", "/" })
	public String demo(Model model) {
		return "classpath:/wechat/jsdkdemo/demo";
	}
}
