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

import com.airshiplay.play.main.entity.MemberRankEntity;
import com.airshiplay.play.main.entity.MemberUserEntity;
import com.airshiplay.play.obd.entity.ObdMemberRankEntity;
import com.airshiplay.play.obd.entity.ObdUserEntity;
import com.airshiplay.play.obd.service.ObdUserEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/obd/user")
public class ObdUserController {

	@Autowired
	private ObdUserEntityService obdUserEntityService;

	@RequestMapping(value = "/userList.view", method = RequestMethod.GET)
	public String getList() {
		return "/bootstrap/obd/user/userList";
	}

	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "/bootstrap/obd/user/userForm";
	}

	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("user", obdUserEntityService.findOne(id));
		return "/bootstrap/obd/user/userForm";
	}

	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("user", obdUserEntityService.findOne(id));
		return "/bootstrap/obd/user/userView";
	}

	
	@RequestMapping(value="/{memberId}/rank/update",method = RequestMethod.GET)
	public String setMemberRank(Model model,@PathVariable Long memberId){
		model.addAttribute("memberId", memberId);
		return  "/bootstrap/obd/user/userRankDialog";
	}
	
	@RequestMapping(value="/{memberId}/rank/update",method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result doSetRank(@PathVariable Long memberId,@RequestParam(value = "id") ObdMemberRankEntity entity){
		ObdUserEntity userEntity= obdUserEntityService.findOne(memberId);
		userEntity.setObdrank(entity);
		obdUserEntityService.save(userEntity);
		return Result.success();
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<ObdUserEntity> doPage(Predicate predicate, Pageable pageable) {
		return obdUserEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid ObdUserEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		obdUserEntityService.save(job);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") ObdUserEntity entity) {
		obdUserEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") ObdUserEntity[] entities) {
		for (ObdUserEntity entity : entities) {
			obdUserEntityService.delete(entity);
		}

		return Result.success();
	}
}
