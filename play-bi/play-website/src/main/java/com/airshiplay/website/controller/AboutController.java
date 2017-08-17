package com.airshiplay.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 关于我们
 * 
 * @author lig
 *
 */
@Controller
@RequestMapping("/about")
public class AboutController extends BaseController {
	
	@RequestMapping
	public String get() {
		return "/about";
	}
}
