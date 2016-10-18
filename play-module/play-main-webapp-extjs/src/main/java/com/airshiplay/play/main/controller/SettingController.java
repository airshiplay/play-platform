package com.airshiplay.play.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.SettingEntity;
import com.airshiplay.play.main.service.SettingEntityService;
import com.airshiplay.play.repo.domain.Result;

@Controller
@RequestMapping("/setting")
public class SettingController {

	@Autowired
	private SettingEntityService settingEntityService;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid SettingEntity setting, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		settingEntityService.save(setting);

		return Result.success();
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public SettingEntity doGet() {
		return settingEntityService.get();
	}

}
