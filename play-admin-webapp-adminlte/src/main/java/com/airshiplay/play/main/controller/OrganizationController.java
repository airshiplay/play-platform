package com.airshiplay.play.main.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

import com.airshiplay.play.main.entity.OrganizationEntity;
import com.airshiplay.play.main.service.OrganizationEntityService;
import com.airlenet.repo.domain.Node;
import com.airlenet.repo.domain.Result;
import com.airlenet.repo.domain.Tree;
import com.google.common.base.Strings;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/center/org")
public class OrganizationController {
	@Autowired
	private OrganizationEntityService organizationEntityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String get(Model model) {
		return "classpath:/admin/org/list";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add() {
		return "classpath:/admin/org/orgForm";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edit(Model model,@PathVariable Long id) {
		model.addAttribute("org", organizationEntityService.findOne(id));
		return "classpath:/admin/org/orgForm";
	}

}
