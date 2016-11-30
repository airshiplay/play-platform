package com.airshiplay.play.wechat.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping(path = { "", "/", "/index" })
	public String get() {
		return "index";
	}
}
