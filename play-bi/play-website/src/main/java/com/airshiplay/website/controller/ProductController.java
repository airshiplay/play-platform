package com.airshiplay.website.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {

	@RequestMapping
	public String get(@RequestParam(defaultValue = "0") int page,
			@RequestParam(required = false) Long cid) {
		return "/product/product";
	}
}
