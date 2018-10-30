package com.airlenet.manage.module.system.controller;

import com.airlenet.manage.module.system.entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.querydsl.core.types.Predicate;

import com.airlenet.webapp.HierarchicalEntityController;

@Controller
@RequestMapping("/system/menu")
public class MenuItemController extends HierarchicalEntityController<MenuItem, Long> {

	@Override
	public Page<MenuItem> doPage(Predicate predicate, Pageable pageable) {
		return super.doInternalPage(predicate, pageable);
	}

}
