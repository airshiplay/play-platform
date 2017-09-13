package com.airshiplay.play.plugin.oauth.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.plugin.oauth.model.OauthPlugin;
import com.airshiplay.play.plugin.oauth.model.OauthUserEntity;
import com.airshiplay.play.plugin.oauth.service.OauthPluginService;
import com.airshiplay.play.plugin.oauth.service.OauthUserService;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/plugin/oauth")
public class AdminPlugOauthController {
	@Autowired
	private OauthPluginService oauthService;
	
	@Autowired
	private OauthUserService oauthUserService;
	@RequestMapping("/list")
	public String list() {
		return "classpath:/plugin/oauth/list";
	}
	@RequestMapping("/view/{oauthPluginId}")
	public String view(Model model,@PathVariable String oauthPluginId) {
		OauthPlugin plugin=	oauthService.getOauthPlugin(oauthPluginId);
		model.addAttribute("plugin", plugin);
		model.addAttribute("plugin_config", plugin.getPluginConfig());
		return "classpath:/plugin/oauth/view";
	}
	@RequestMapping("/setting/{oauthPluginId}")
	public String install(Model model,@PathVariable String oauthPluginId) {
		OauthPlugin plugin=	oauthService.getOauthPlugin(oauthPluginId);
		model.addAttribute("plugin", plugin);
		model.addAttribute("plugin_config", plugin.getPluginConfig());
		return "classpath:/plugin/oauth/setting";
	}
	
	@RequestMapping(value="/user/list",method = RequestMethod.GET)
	public String getUsrList(){
		return "classpath:/plugin/oauth/user/userList";
	}
	@RequestMapping(value = "/user/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<OauthUserEntity> doPage(Predicate predicate, Pageable pageable) {
		return oauthUserService.findAll(predicate, pageable);
	}
}
