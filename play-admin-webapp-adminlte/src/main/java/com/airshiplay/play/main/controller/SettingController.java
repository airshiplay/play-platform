package com.airshiplay.play.main.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.SettingEntity;
import com.airshiplay.play.main.service.SettingEntityService;
import com.airlenet.repo.domain.Result;

@Controller
@RequestMapping("/center/setting")
public class SettingController {

	@Autowired
	private SettingEntityService settingEntityService;

	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String getList(Model model) {
		SettingEntity setting = settingEntityService.get();
		model.addAttribute("setting", setting);
		return "classpath:/admin/setting/info";
	}


}
