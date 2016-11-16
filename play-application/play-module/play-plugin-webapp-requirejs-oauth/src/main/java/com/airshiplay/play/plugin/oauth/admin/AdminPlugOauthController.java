package com.airshiplay.play.plugin.oauth.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airshiplay.play.plugin.oauth.model.OauthPlugin;
import com.airshiplay.play.plugin.oauth.service.OauthPluginService;

@Controller
@RequestMapping("/plugin/oauth")
public class AdminPlugOauthController {
	@Autowired
	private OauthPluginService oauthService;
	
	@RequestMapping("/list")
	public String list() {
		return "/views/freemarker/plugin/oauth/list";
	}
	@RequestMapping("/view/{oauthPluginId}")
	public String view(Model model,@PathVariable String oauthPluginId) {
		OauthPlugin plugin=	oauthService.getOauthPlugin(oauthPluginId);
		model.addAttribute("plugin", plugin);
		model.addAttribute("plugin_config", plugin.getPluginConfig());
		return "/views/freemarker/plugin/oauth/view";
	}
	@RequestMapping("/setting/{oauthPluginId}")
	public String install(Model model,@PathVariable String oauthPluginId) {
		OauthPlugin plugin=	oauthService.getOauthPlugin(oauthPluginId);
		model.addAttribute("plugin", plugin);
		model.addAttribute("plugin_config", plugin.getPluginConfig());
		return "/views/freemarker/plugin/oauth/setting";
	}
}
