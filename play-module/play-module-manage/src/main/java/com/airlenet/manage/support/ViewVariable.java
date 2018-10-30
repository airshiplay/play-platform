package com.airlenet.manage.support;

import java.util.List;

import com.airlenet.manage.module.system.entity.User;
import com.airlenet.manage.module.system.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import com.airlenet.data.domain.Userable;
import com.airlenet.thymeleaf.ThymeleafMenuItem;
import com.airlenet.thymeleaf.ThymeleafViewVariable;

@Component
public class ViewVariable implements ThymeleafViewVariable {

	@Autowired
	private MenuItemService menuItemService;

	@Autowired
	private AuditorAware<User> auditorAware;

	@Override
	public Userable getCurrentUser() {
		return auditorAware.getCurrentAuditor().isPresent()?auditorAware.getCurrentAuditor().get():null;
	}

	@Override
	public List<? extends ThymeleafMenuItem<?>> getMenuRoots() {
		return menuItemService.findRoots();
	}

}
