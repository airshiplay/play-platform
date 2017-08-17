package com.airshiplay.play.main.controller;

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
import com.airshiplay.play.main.entity.QMemberUserEntity;
import com.airshiplay.play.main.service.MemberRankEntityService;
import com.airshiplay.play.main.service.MemberUserEntityService;
import com.airshiplay.play.repo.domain.Result;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/center/memberRank")
public class MemberRankController {

	@Autowired
	private MemberRankEntityService memberRankEntityService;
	
	@Autowired
	private MemberUserEntityService memberUserEntityService;

	@RequestMapping(value = "/memberRankList.view", method = RequestMethod.GET)
	public String getList() {
		return "/bootstrap/admin/memberRank/memberRankList";
	}

	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "/bootstrap/admin/memberRank/memberRankForm";
	}

	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("memberRank", memberRankEntityService.findOne(id));
		return "/bootstrap/admin/memberRank/memberRankForm";
	}

	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("memberRank", memberRankEntityService.findOne(id));
		return "/bootstrap/admin/memberRank/memberRankView";
	}

	@RequestMapping(value = { "/{id}/member.view" }, method = RequestMethod.GET)
	public String getMember(Model model, @PathVariable Long id) {
		model.addAttribute("memberRankId",id);
		return "/bootstrap/admin/memberRank/rankMemberDialog";
	}
	
	@RequestMapping(value = "/{id}/member/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<MemberUserEntity> doMemberPage(@PathVariable Long id,Predicate predicate, Pageable pageable) {
		return memberUserEntityService.findAll(QMemberUserEntity.memberUserEntity.rank.id.eq(id).and(predicate), pageable);
	}
	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<MemberRankEntity> doPage(Predicate predicate, Pageable pageable) {
		return memberRankEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public Result doSave(@ModelAttribute @Valid MemberRankEntity job, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return Result.validateError();
		}

		memberRankEntityService.save(job);

		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "id")
	@ResponseBody
	public Result delete(@RequestParam(value = "id") MemberRankEntity entity) {
		memberRankEntityService.delete(entity);
		return Result.success();
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "ids")
	@ResponseBody
	public Result batchDelete(@RequestParam(value = "ids") MemberRankEntity[] entities) {
		for (MemberRankEntity entity : entities) {
			memberRankEntityService.delete(entity);
		}

		return Result.success();
	}
}
