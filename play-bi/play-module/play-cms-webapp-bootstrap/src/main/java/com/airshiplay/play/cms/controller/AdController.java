package com.airshiplay.play.cms.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.cms.entity.AdEntity;
import com.airshiplay.play.cms.service.AdEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/cms/ad")
public class AdController {

	@Autowired
	private AdEntityService adEntityService;

	@RequestMapping(value = "/adList.view", method = RequestMethod.GET)
	public String getAdList() {
		return "/bootstrap/cms/ad/adList";
	}

	@RequestMapping(value = {"/create.view"}, method = RequestMethod.GET)
	public String getAdCreate(Model model) {
		return "/bootstrap/cms/ad/adForm";
	}
	
	@RequestMapping(value = {"/edit/{id}.view"}, method = RequestMethod.GET)
	public String getAdEdit(@PathVariable(value = "id") AdEntity entity,Model model) {
		model.addAttribute("ad", entity);
		return "/bootstrap/cms/ad/adForm";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<AdEntity> doPage(Predicate predicate, Pageable pageable) {
		return adEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid AdEntity ad,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		adEntityService.save(ad);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") AdEntity entity) {
		adEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") AdEntity[] entities) {
		for (AdEntity entity : entities) {
			adEntityService.delete(entity);
		}

		return Result.success();
	}
}
