package com.airshiplay.play.wechat.controller;

import java.util.concurrent.Callable;

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

import com.airshiplay.play.repo.domain.Result;
import com.airshiplay.play.wechat.entity.AttentionUserEntity;
import com.airshiplay.play.wechat.service.AttentionUserEntityService;
import com.airshiplay.play.wechat.service.WeixinUserService;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/wechat/attentionUser")
public class AttentionUserController {

	@Autowired
	private AttentionUserEntityService attentionUserEntityService;

	@Autowired
	private WeixinUserService wexinUserService;

	@RequestMapping(value = "/attentionUserList.view", method = RequestMethod.GET)
	public String getList() {
		return "classpath:/wechat/attentionUser/attentionUserList";
	}

	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/wechat/attentionUser/attentionUserForm";
	}

	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("attentionUser", attentionUserEntityService.findOne(id));
		return "classpath:/wechat/attentionUser/attentionUserForm";
	}

	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("attentionUser", attentionUserEntityService.findOne(id));
		return "classpath:/wechat/attentionUser/attentionUserView";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<AttentionUserEntity> doPage(Predicate predicate, Pageable pageable) {
		return attentionUserEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid AttentionUserEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		attentionUserEntityService.save(job);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") AttentionUserEntity entity) {
		attentionUserEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") AttentionUserEntity[] entities) {
		for (AttentionUserEntity entity : entities) {
			attentionUserEntityService.delete(entity);
		}

		return Result.success();
	}

	@RequestMapping(value = "/asyncUser", method = RequestMethod.POST)
	@ResponseBody
	public Callable<Result> asyncUser(@RequestParam(value = "id") Long id) {
		
		return new Callable<Result>() {
			@Override
			public Result call() throws Exception {
				wexinUserService.syncUser(id);
				return Result.success();
			}
		};
	}

	@RequestMapping(value = "/asyncUserDetail", method = RequestMethod.POST)
	@ResponseBody
	public Callable<Result> asyncUserDetail(@RequestParam(value = "id") Long id) {
		return new Callable<Result>() {

			@Override
			public Result call() throws Exception {
				wexinUserService.syncUserDetail(id);
				return Result.success();
			}
		};
	}
}
