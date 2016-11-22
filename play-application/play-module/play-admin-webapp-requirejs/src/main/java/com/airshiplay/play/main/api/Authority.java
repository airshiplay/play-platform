package com.airshiplay.play.main.api;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.joda.time.DateTime;

import com.airshiplay.play.main.entity.AuthorityEntity;
import com.airshiplay.play.main.entity.AuthorityEntity.PermissionType;
import com.airshiplay.play.main.entity.MenuEntity;
import com.airshiplay.play.main.entity.RoleEntity;

public class Authority implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2793020115205927883L;
	private Boolean checked;

	@NotNull
	private PermissionType type;
	/**
	 * 菜单读权限
	 */
	@Size(max = 10)
	private String name;
	/**
	 * sys:menu:view
	 */
	private String permission;

	private MenuEntity menu;

	private Set<RoleEntity> roles = new HashSet<>();
	private Integer sortNo;

	private Long id;
	private Date createdDate;

	private Date lastModifiedDate;

	public Authority(AuthorityEntity entity) {
		this.setId(entity.getId());
		this.setCreatedDate(entity.getCreatedDate());
		this.setLastModifiedDate(entity.getLastModifiedDate());
		this.setMenu(entity.getMenu());
		this.setName(entity.getName());
		this.setPermission(entity.getPermission());
		this.setSortNo(entity.getSortNo());
		this.setType(entity.getType());
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}

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

	public Authority() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public DateTime getCreatedDate() {
		return null == createdDate ? null : new DateTime(createdDate);
	}

	public void setCreatedDate(final DateTime createdDate) {
		this.createdDate = null == createdDate ? null : createdDate.toDate();
	}

	public DateTime getLastModifiedDate() {
		return null == lastModifiedDate ? null : new DateTime(lastModifiedDate);
	}

	public void setLastModifiedDate(DateTime lastModifiedDate) {
		this.lastModifiedDate = null == lastModifiedDate ? null
				: lastModifiedDate.toDate();
	}

	public Set<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(Set<RoleEntity> roles) {
		this.roles = roles;
	}

}
