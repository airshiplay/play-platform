package com.airlenet.scheduler.support;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;

import com.airlenet.data.domain.Userable;
import com.airlenet.thymeleaf.DefaultMenuItem;
import com.airlenet.thymeleaf.DefaultUser;
import com.airlenet.thymeleaf.ThymeleafMenuItem;
import com.airlenet.thymeleaf.ThymeleafViewVariable;

@Component
public class ViewVariable implements ThymeleafViewVariable {

	private Userable currentUser = new DefaultUser("管理员");

	@Override
	public Userable getCurrentUser() {
		return currentUser;
	}

	@Override
	public List<? extends ThymeleafMenuItem<?>> getMenuRoots() {
		return Lists.newArrayList(DefaultMenuItem.create("我的工作台", "dashboard", "fa fa-dashboard", "/", null),
				DefaultMenuItem.create("作业列表", "job", "fa fa-tasks", "/job", null));
	}

}
