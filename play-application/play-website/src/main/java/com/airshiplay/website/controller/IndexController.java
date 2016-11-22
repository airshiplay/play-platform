package com.airshiplay.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping()
public class IndexController extends BaseController {

	@RequestMapping(value = { "", "/", "/index" }, method = RequestMethod.GET)
	public String get() {
		return "/index";
	}

	@RequestMapping(value = { "/template" }, method = RequestMethod.GET)
	public String template() {
		return "/template";
	}
}
