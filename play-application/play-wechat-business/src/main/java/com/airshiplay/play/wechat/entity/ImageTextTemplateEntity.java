package com.airshiplay.play.wechat.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 图文消息
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "wechat_imgtext_template")
@Getter
@Setter
public class ImageTextTemplateEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 模板名称
	 */
	@Column
	@NotNull
	@Size(min = 1, max = 20)
	private String title;
	/**
	 * 模板内容
	 * 
	 * @formtype textarea
	 */
	@Column(length = 500)
	@NotNull
	@Size(max = 200)
	private String content;

	@OneToMany
	private Set<ImageTextItemEntity> items;

}
