package com.airshiplay.play.main.api;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;

import com.airshiplay.play.main.entity.AuthorityEntity;

public class Authority extends AuthorityEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2793020115205927883L;
	private Boolean checked;

	public Authority() {

	}

	public Authority(AuthorityEntity entity) throws IllegalAccessException,
			InvocationTargetException {
		BeanUtils.copyProperties(this, entity);
		this.setId(entity.getId());
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

}
