package com.airshiplay.website.controller;

import java.util.Date;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import com.airshiplay.play.web.bind.BinderDateFormatter;

@Controller
@RequestMapping("/news")
public class NewsController extends BaseController {

	@RequestMapping
	
	public String get(@RequestParam(defaultValue = "0") int page,
			@RequestParam(required = false) Long cid) {
		LocaleContextHolder.getLocale().getLanguage();
		return "/news";
	}

	@InitBinder
	public void binder(WebDataBinder binder) {
//		binder.addCustomFormatter(new BinderDateFormatter());
	}

	@RequestMapping("/{date}/{id}")
	
	public String getDetail(@PathVariable Date date, @PathVariable Long id) {
		return "/news/detail";
	}
}
