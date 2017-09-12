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

import com.querydsl.core.types.Predicate;
import com.airshiplay.play.cms.entity.AdPositionEntity;
import com.airshiplay.play.cms.service.AdPositionEntityService;
import com.airlenet.repo.domain.Result;

@Controller
@RequestMapping("/cms/adPosition")
public class AdPositionController {

	@Autowired
	private AdPositionEntityService adPositionEntityService;

	@RequestMapping(value = "/adPositionList.view", method = RequestMethod.GET)
	public String getAdList() {
		return "/bootstrap/cms/adPosition/adPositionList";
	}
	
	@RequestMapping(value = {"/create.view"}, method = RequestMethod.GET)
	public String getAdCreate(Model model) {
		return "/bootstrap/cms/adPosition/adPositionForm";
	}
	
	@RequestMapping(value = {"/edit/{id}.view"}, method = RequestMethod.GET)
	public String getAdEdit(Model model,@PathVariable Long id) {
		model.addAttribute("adPosition", adPositionEntityService.findOne(id));
		return "/bootstrap/cms/adPosition/adPositionForm";
	}
	
	@RequestMapping(value = "/page", method ={ RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public Page<AdPositionEntity> doPage(Predicate predicate, Pageable pageable) {
		return adPositionEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid AdPositionEntity adPosition, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		adPositionEntityService.save(adPosition);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") AdPositionEntity entity) {
		adPositionEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") AdPositionEntity[] entities) {
		for (AdPositionEntity entity : entities) {
			adPositionEntityService.delete(entity);
		}

		return Result.success();
	}
}
