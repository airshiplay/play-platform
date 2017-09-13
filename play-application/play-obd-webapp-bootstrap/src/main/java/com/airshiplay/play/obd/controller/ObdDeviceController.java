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

import com.airshiplay.play.obd.entity.ObdDeviceEntity;
import com.airshiplay.play.obd.service.ObdDeviceEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/obd/obdDevice")
public class ObdDeviceController {

	@Autowired
	private ObdDeviceEntityService obdDeviceEntityService;

	@RequestMapping(value = "/obdDeviceList.view", method = RequestMethod.GET)
	public String getList() {
		return "classpath:/obd/obdDevice/obdDeviceList";
	}

	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/obd/obdDevice/obdDeviceForm";
	}

	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("obdDevice", obdDeviceEntityService.findOne(id));
		return "classpath:/obd/obdDevice/obdDeviceForm";
	}

	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("obdDevice", obdDeviceEntityService.findOne(id));
		return "classpath:/obd/obdDevice/obdDeviceView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<ObdDeviceEntity> doPage(Predicate predicate, Pageable pageable) {
		return obdDeviceEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid ObdDeviceEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		obdDeviceEntityService.save(job);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") ObdDeviceEntity entity) {
		obdDeviceEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") ObdDeviceEntity[] entities) {
		for (ObdDeviceEntity entity : entities) {
			obdDeviceEntityService.delete(entity);
		}

		return Result.success();
	}
}
