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
import com.airshiplay.play.main.service.MemberRankEntityService;
import com.airshiplay.play.main.service.MemberUserEntityService;
import com.airlenet.repo.domain.Result;
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
		return "classpath:/admin/memberRank/memberRankList";
	}

	@RequestMapping(value = { "/create.view" }, method = RequestMethod.GET)
	public String create(Model model) {
		return "classpath:/admin/memberRank/memberRankForm";
	}

	@RequestMapping(value = { "/edit/{id}.view" }, method = RequestMethod.GET)
	public String edit(Model model, @PathVariable Long id) {
		model.addAttribute("memberRank", memberRankEntityService.findOne(id));
		return "classpath:/admin/memberRank/memberRankForm";
	}

	@RequestMapping(value = { "/view/{id}.view" }, method = RequestMethod.GET)
	public String view(Model model, @PathVariable Long id) {
		model.addAttribute("memberRank", memberRankEntityService.findOne(id));
		return "classpath:/admin/memberRank/memberRankView";
	}

	@RequestMapping(value = { "/{id}/member.view" }, method = RequestMethod.GET)
	public String getMember(Model model, @PathVariable Long id) {
		model.addAttribute("memberRankId",id);
		return "classpath:/admin/memberRank/rankMemberDialog";
	}

}
