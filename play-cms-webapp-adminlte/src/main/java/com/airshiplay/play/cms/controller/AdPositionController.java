package com.airshiplay.play.cms.controller;

import com.airshiplay.play.cms.service.AdPositionEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/cms/adPosition")
public class AdPositionController {

	@Autowired
	private AdPositionEntityService adPositionEntityService;

	@RequestMapping(value = "/adPositionList.view", method = RequestMethod.GET)
	public String getAdList() {
		return "classpath:/cms/adPosition/adPositionList";
	}
	
	@RequestMapping(value = {"/create.view"}, method = RequestMethod.GET)
	public String getAdCreate(Model model) {
		return "classpath:/cms/adPosition/adPositionForm";
	}
	
	@RequestMapping(value = {"/edit/{id}.view"}, method = RequestMethod.GET)
	public String getAdEdit(Model model,@PathVariable Long id) {
		model.addAttribute("adPosition", adPositionEntityService.findOne(id));
		return "classpath:/cms/adPosition/adPositionForm";
	}

}
