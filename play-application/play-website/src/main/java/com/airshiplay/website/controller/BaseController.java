package com.airshiplay.website.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.airshiplay.play.cms.entity.AdEntity;
import com.airshiplay.play.cms.entity.AdEntity.Type;
import com.airshiplay.play.cms.service.AdEntityService;

public class BaseController {
	@Autowired
	AdEntityService adEntityService;

	@ModelAttribute
	public void golab(Model model) {
		List<AdEntity> carousel = adEntityService.findTop3("home-carousel", Type.image);
		model.addAttribute("carouseList", carousel);
	}
}
