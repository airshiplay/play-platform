package com.airshiplay.play.obd.controller;

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

import com.airshiplay.play.cms.entity.JobEntity;
import com.airshiplay.play.obd.entity.Store4SEntity;
import com.airshiplay.play.obd.service.Store4SEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/obd/store4S")
public class Store4SController {

	@Autowired
	private Store4SEntityService store4SEntityService;

	@RequestMapping(value = "/store4SList.view", method = RequestMethod.GET)
	public String getList() {
		return "/bootstrap/obd/store4S/store4SList";
	}

	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "/bootstrap/obd/store4S/store4SForm";
	}

	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("store4S", store4SEntityService.findOne(id));
		return "/bootstrap/obd/store4S/store4SForm";
	}

	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("store4S", store4SEntityService.findOne(id));
		return "/bootstrap/obd/store4S/store4SView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<Store4SEntity> doPage(Predicate predicate, Pageable pageable) {
		return store4SEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid Store4SEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		store4SEntityService.save(job);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") Store4SEntity entity) {
		store4SEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") Store4SEntity[] entities) {
		for (Store4SEntity entity : entities) {
			store4SEntityService.delete(entity);
		}

		return Result.success();
	}
}
