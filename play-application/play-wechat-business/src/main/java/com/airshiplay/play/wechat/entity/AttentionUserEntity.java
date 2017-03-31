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
 * 关注用户
 * 
 * @name 关注用户
 * @version 1.0.0
 * @author lig
 */
@Table(name = "wechat_attention_user")
@Entity
@Getter
@Setter
public class AttentionUserEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * openid
	 */
	private String openid;
	/**
	 * 昵称
	 */
	@NotNull
	@Size(min = 2, max = 50)
	@Column(length = 100)
	private String nickname;
	/**
	 * 性别
	 */
	@Column
	private int sex;
	/**
	 * 语言
	 */
	@Column
	private String language;
	/**
	 * 城市
	 */
	@Column
	private String city;
	/**
	 * 省份
	 */
	@Column
	private String province;
	/**
	 * 国家
	 */
	@Column
	private String country;
	/**
	 * 头像
	 * 
	 * @formtype image
	 */
	@Column(length = 512)
	private String headimgurl;

	@Column
	private String unionid;
	@Column
	private String groupid;
	/**
	 * 1关注，2取消关注，0关注
	 */
	@Column
	private int status;

	@ManyToOne
	@JoinColumn(name = "config_id")
	private WechatConfigEntity config;
}
