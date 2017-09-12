package com.airshiplay.play.plugin.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airshiplay.play.plugin.controller.PluginController;
import com.airshiplay.play.plugin.oauth.model.WeiboOauthPlugin;

@Controller
@RequestMapping("/oauth/weibo")
public class WeiboOauthController extends PluginController<WeiboOauthPlugin> {

}
