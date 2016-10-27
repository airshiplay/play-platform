package com.airshiplay.play.plugin.oauth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/plugin/oauth")
public class PlugOauthAdminController {

	@RequestMapping("/list")
	public String list() {
		return "/views/plugin/oauth/list";
	}
}
