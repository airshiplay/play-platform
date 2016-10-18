package com.airshiplay.play.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.repo.MenuEntityRepository;

@Service
public class MenuEntityService extends HierarchicalEntityService<MenuEntity, Long> {

	@Autowired
	private MenuEntityRepository menuEntityRepository;

	public MenuEntity findByCode(String code) {
		return menuEntityRepository.findByCode(code);
	}

}
