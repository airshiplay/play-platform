package com.airshiplay.play.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.entity.QRoleEntity;
import com.airshiplay.play.main.entity.RoleEntity;
import com.airshiplay.play.main.repo.MenuEntityRepository;
import com.airshiplay.play.main.repo.RoleEntityRepository;
import com.airshiplay.play.repo.domain.Tree;
import com.google.common.collect.Lists;

@Service
public class MenuEntityService extends HierarchicalEntityService<MenuEntity, Long> {

	@Autowired
	private MenuEntityRepository menuEntityRepository;

	@Autowired
	private RoleEntityRepository roleEntityRepository;

	@Transactional(readOnly = true)
	public MenuEntity findByCode(String code) {
		return menuEntityRepository.findByCode(code);
	}

	@Transactional(readOnly = true)
	public Tree<MenuEntity> getAllMenuTree() {
		Tree<MenuEntity> tree = findTree(null);
		return tree;
	}

	@Transactional(readOnly = true)
	public Tree<MenuEntity> getMenuTreeByUserId(Long userId) {
		RoleEntity roleEntity = roleEntityRepository.findOne(QRoleEntity.roleEntity.users.any().id.eq(userId));
		if (roleEntity.getCode().equals("superadmin")) {
			return findTree(null);
		}
		Tree<MenuEntity> tree = toTree(null, Lists.newArrayList(roleEntity.getMenus()));
		return tree;
	}
}
