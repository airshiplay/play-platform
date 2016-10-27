package com.airshiplay.play.oauth2.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.oauth2.entity.AccessTokenEntity;
import com.airshiplay.play.oauth2.service.AccessTokenEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

/**
 * OAuth2后台页面管理
 * 
 * @author lig
 *
 */
@Controller
@RequestMapping("/oauth2/oauth2")
public class Oauth2Controller {

	@Autowired
	AccessTokenEntityService appService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList() {
		return "/views/oauth2/app/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAdd() {
		return "/views/app/app/add";
	}

	@RequestMapping(value = "/addstore", method = RequestMethod.GET)
	public String getAddstore() {
		return "/views/app/app/addstore";
	}

	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String getView(@PathVariable Long id, Model model) {
		model.addAttribute("app", appService.findOne(id));
		return "/views/app/app/view";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Long id, Model model) {
		model.addAttribute("app", appService.findOne(id));
		return "/views/app/app/edit";
	}

	@RequestMapping(value = "/dialog/storeAppList", method = RequestMethod.GET)
	public String getDialogStoreAppList() {
		return "/views/app/app/dialog/storeAppList";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid AccessTokenEntity app,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}
		appService.save(app);

		return Result.success();
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<AccessTokenEntity> doPage(Predicate predicate, Pageable pageable) {
		return appService.findAll(predicate, pageable);
	}
}
