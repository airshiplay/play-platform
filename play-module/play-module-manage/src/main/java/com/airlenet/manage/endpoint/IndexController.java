package com.airlenet.manage.endpoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

	@RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
	public String get() {
		return "index";
	}
}
