package com.airshiplay.play.website.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

@Entity
@Table(name = "website_config")
public class WebsiteConfig extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
