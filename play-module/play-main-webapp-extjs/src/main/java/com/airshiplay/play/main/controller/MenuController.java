package com.airshiplay.play.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.querydsl.core.types.Predicate;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.service.MenuEntityService;
import com.airshiplay.play.repo.domain.Tree;

@Controller
@RequestMapping("/menu")
public class MenuController {

	@Autowired
	private MenuEntityService menuEntityService;

	@RequestMapping(value = "/tree", method = RequestMethod.GET)
	@ResponseBody
	public Tree<MenuEntity> tree(Predicate predicate) {
		Tree<MenuEntity> tree = menuEntityService.findTree(predicate);
		tree.setIconClsProperty("iconCls");
		tree.setTextProperty("text");
		return tree;
	}

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	@ResponseBody
	public Page<MenuEntity> page(Predicate predicate, Pageable pageable) {
		return menuEntityService.findAll(predicate, pageable);
	}

}
