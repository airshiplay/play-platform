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

import com.airshiplay.play.obd.entity.ReserveEntity;
import com.airshiplay.play.obd.service.ReserveEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/obd/reserve")
public class ReserveController {

	@Autowired
	private ReserveEntityService reserveEntityService;

	@RequestMapping(value = "/reserveList.view", method = RequestMethod.GET)
	public String getList() {
		return "classpath:/obd/reserve/reserveList";
	}

	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/obd/reserve/reserveForm";
	}

	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("reserve", reserveEntityService.findOne(id));
		return "classpath:/obd/reserve/reserveForm";
	}

	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("reserve", reserveEntityService.findOne(id));
		return "classpath:/obd/reserve/reserveView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<ReserveEntity> doPage(Predicate predicate, Pageable pageable) {
		return reserveEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid ReserveEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		reserveEntityService.save(job);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") ReserveEntity entity) {
		reserveEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") ReserveEntity[] entities) {
		for (ReserveEntity entity : entities) {
			reserveEntityService.delete(entity);
		}

		return Result.success();
	}
}
