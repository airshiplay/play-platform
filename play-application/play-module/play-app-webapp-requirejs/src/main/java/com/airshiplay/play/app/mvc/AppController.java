package com.airshiplay.play.app.mvc;

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

import com.airshiplay.play.app.entity.AppEntity;
import com.airshiplay.play.app.service.AppEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/app/app")
public class AppController {

	@Autowired
	AppEntityService appService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList() {
		return "/views/freemarker/app/app/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAdd() {
		return "/views/freemarker/app/app/add";
	}
	@RequestMapping(value = "/addstore", method = RequestMethod.GET)
	public String getAddstore() {
		return "/views/freemarker/app/app/addstore";
	}
	
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String getView(@PathVariable Long id,Model model) {
		model.addAttribute("app", appService.findOne(id));
		return "/views/freemarker/app/app/view";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String getEdit(@PathVariable Long id,Model model) {
		model.addAttribute("app", appService.findOne(id));
		return "/views/freemarker/app/app/edit";
	}
	
	@RequestMapping(value = "/dialog/storeAppList", method = RequestMethod.GET)
	public String getDialogStoreAppList() {
		return "/views/freemarker/app/app/dialog/storeAppList";
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid AppEntity app,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}
		appService.save(app);

		return Result.success();
	}
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<AppEntity> doPage(Predicate predicate, Pageable pageable) {
		return appService.findAll(predicate, pageable);
	}
}
