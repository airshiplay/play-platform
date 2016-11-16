package com.airshiplay.play.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.airshiplay.play.main.entity.OrganizationEntity;
import com.airshiplay.play.main.service.OrganizationEntityService;
import com.airshiplay.play.repo.domain.Tree;
import com.querydsl.core.types.Predicate;

@Controller
@RequestMapping("/center/org")
public class OrganizationController {
	@Autowired
	private OrganizationEntityService organizationEntityService;

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String get(Model model) {
		Tree<OrganizationEntity> tree = organizationEntityService
				.findTree(null);
//		tree.setIconClsProperty("iconCls");
		tree.setTextProperty("name");
		model.addAttribute("allOrgTree", tree.getRoots());

		return "/views/freemarker/admin/org/list";
	}

	@RequestMapping(value = "/page", method = RequestMethod.POST)
	@ResponseBody
	public Page<OrganizationEntity> page(Predicate predicate, Pageable pageable) {
		return organizationEntityService.findAll(predicate, pageable);
	}

	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	@ResponseBody
	public Tree<OrganizationEntity> tree(Predicate predicate) {
		Tree<OrganizationEntity> tree = organizationEntityService
				.findTree(predicate);
		// tree.setIconClsProperty("icon");
		tree.setTextProperty("name");
		return tree;
	}
}
