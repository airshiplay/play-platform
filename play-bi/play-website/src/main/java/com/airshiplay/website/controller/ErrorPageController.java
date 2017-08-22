package com.airshiplay.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorPageController extends BaseController {

	@RequestMapping(value = { "/notFound", "/404" })
	public String notFound(HttpServletRequest request) {
		return "/404";
	}
}
