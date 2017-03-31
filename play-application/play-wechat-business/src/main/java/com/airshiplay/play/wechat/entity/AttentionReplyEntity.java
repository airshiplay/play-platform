package com.airshiplay.play.wechat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 关注回复
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "wechat_attention_reply")
@Getter
@Setter
public class AttentionReplyEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @formtype select
	 */
	@OneToOne
	@JoinColumn(name = "config_id")
	private WechatConfigEntity config;
	/**
	 * @formtype select
	 */
	@OneToOne
	private TextTemplateEntity text;
	/**
	 * @formtype select
	 */
	@OneToOne
	private ImageTextTemplateEntity imageText;
	/**
	 * 消息类型
	 */
	@Column
	@Enumerated
	private MessageType msgType;
}
