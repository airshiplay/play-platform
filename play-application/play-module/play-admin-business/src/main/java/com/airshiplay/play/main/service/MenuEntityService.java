package com.airshiplay.play.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.repo.MenuEntityRepository;
import com.airshiplay.play.repo.domain.Tree;

@Service
public class MenuEntityService extends
		HierarchicalEntityService<MenuEntity, Long> {

	@Autowired
	private MenuEntityRepository menuEntityRepository;

	@Transactional(readOnly = true)
	public MenuEntity findByCode(String code) {
		return menuEntityRepository.findByCode(code);
	}

	@Transactional(readOnly = true)
	public Tree<MenuEntity> getAllMenuTree() {
		Tree<MenuEntity> tree = findTree(null);
		tree.setIconClsProperty("iconCls");
		tree.setTextProperty("text");
		return tree;
	}

}
