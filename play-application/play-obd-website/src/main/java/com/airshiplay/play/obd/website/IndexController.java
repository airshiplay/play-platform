package com.airshiplay.play.obd.website;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping()
public class IndexController {
	@RequestMapping(path = { "", "/" })
	public String get() {
		return "index";
	}
}
