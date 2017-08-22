package com.airshiplay.play.main.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class Authority implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2793020115205927883L;
	private Boolean checked;

	/**
	 * 菜单读权限
	 */
	@Size(max = 10)
	private String name;
	/**
	 * sys:menu:view
	 */
	private String permission;

	private Integer sortNo;

	private Long id;
	private Date createdDate;

	private Date lastModifiedDate;

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

}
