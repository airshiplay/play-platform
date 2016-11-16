package com.airshiplay.play.main.entity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.airshiplay.play.repo.jpa.SortEntity;

@Entity
@Table(name = "sys_authority")
public class AuthorityEntity extends SortEntity<UserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	@Enumerated(EnumType.ORDINAL)
	private PermissionType type;
	/**
	 * 菜单读权限
	 */
	@Column(length = 20)
	@Size(max = 10)
	private String name;
	/**
	 * sys:menu:view
	 */
	@Column(name = "permission")
	private String permission;

	@ManyToOne
	private MenuEntity menu;

	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private Set<RoleEntity> roles = new HashSet<>();
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public MenuEntity getMenu() {
		return menu;
	}

	public void setMenu(MenuEntity menu) {
		this.menu = menu;
	}

	public PermissionType getType() {
		return type;
	}

	public void setType(PermissionType type) {
		this.type = type;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public enum PermissionType {
		/** 页面授权 */
		page, /** 数据授权 */
		data, /** 页面数据授权 */
		all
	}
}
