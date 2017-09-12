package com.airshiplay.play.plugin.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airshiplay.play.plugin.controller.PluginController;
import com.airshiplay.play.plugin.oauth.model.WeChatOpenOauthPlugin;

@Controller
@RequestMapping("/oauth/wechatopen")
public class WeChatOpenOauthController extends PluginController<WeChatOpenOauthPlugin> {

}
