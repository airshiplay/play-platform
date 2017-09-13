package com.airshiplay.play.cms.controller;

import com.airshiplay.play.cms.entity.AdEntity;
import com.airshiplay.play.cms.service.AdEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cms/ad")
public class AdController {

	@Autowired
	private AdEntityService adEntityService;

	@RequestMapping(value = "/adList.view", method = RequestMethod.GET)
	public String getAdList() {
		return "classpath:/cms/ad/adList";
	}

	@RequestMapping(value = {"/create.view"}, method = RequestMethod.GET)
	public String getAdCreate(Model model) {
		return "classpath:/cms/ad/adForm";
	}
	
	@RequestMapping(value = {"/edit/{id}.view"}, method = RequestMethod.GET)
	public String getAdEdit(@PathVariable(value = "id") AdEntity entity,Model model) {
		model.addAttribute("ad", entity);
		return "classpath:/cms/ad/adForm";
	}


}
