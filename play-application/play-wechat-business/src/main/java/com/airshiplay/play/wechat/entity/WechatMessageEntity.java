package com.airshiplay.play.wechat.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.repo.jpa.BaseEntity;

/**
 * 消息管理
 * 
 * @author lig
 *
 */
@Getter
@Setter
@Entity
@Table(name = "wechat_message")
public class WechatMessageEntity extends BaseEntity<Long> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1735847401149945674L;
	@Column(name = "msg_id")
	private String msgId;
	@Column(name = "msg_type")
	private String msgType;
	@Column(name = "to_username")
	private String toUsername;
	@Column(name = "from_username")
	private String fromUsername;
	@Column(name = "create_time")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	@Column(name = "timestamp")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	@Lob
	@Column(name = "detail")
	private String detail;
	@ManyToOne
	@JoinColumn(name = "attention_uid")
	private AttentionUserEntity user;

	@ManyToOne
	@JoinColumn(name = "config_id")
	private WechatConfigEntity config;
}
