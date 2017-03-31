package com.airshiplay.play.wechat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 图文子集
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "wechat_imgtext_item")
@Getter
@Setter
public class ImageTextItemEntity extends DataEntity<AdminUserEntity, Long> {

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

	@ManyToOne
	@JoinColumn(name = "parentId")
	private ImageTextTemplateEntity parent;
}
