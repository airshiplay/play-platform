package com.airshiplay.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 联系我们
 * 
 * @author lig
 *
 */
@Controller
@RequestMapping("/contact")
public class ContactController extends BaseController{
	@RequestMapping
	 
	public String get() {
		return "/contact";
	}
}
