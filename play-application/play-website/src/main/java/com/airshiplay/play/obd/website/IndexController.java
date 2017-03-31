package com.airshiplay.play.obd.website;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.airshiplay.play.cms.entity.AdEntity;
import com.airshiplay.play.cms.entity.AdEntity.Type;
import com.airshiplay.play.cms.service.AdEntityService;

@Controller
@RequestMapping("/obdsite")
public class IndexController {
	@Autowired
	AdEntityService adEntityService;

	@RequestMapping(path = { "", "/" })
	public String get(Model model) {
		List<AdEntity> carousel = adEntityService.findTop3("obdhome-carousel", Type.image);
		model.addAttribute("carouseList", carousel);
		return "obd/index";
	}

	@RequestMapping(path = { "/login" })
	public String login() {
		return "obd/login";
	}

	@RequestMapping(path = { "/reg" })
	public String reg() {
		return "obd/reg";
	}
}
