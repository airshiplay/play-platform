package com.airshiplay.play.wechat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 公众号配置
 * 
 * @author lig
 *
 */
@Entity
@Table(name = "wechat_config")
@Getter
@Setter
public class WechatConfigEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 公众号名称
	 */
	@Column
	@NotNull
	private String name;
	/**
	 * 公众微信号
	 */
	@Column
	private String wechatNo;
	/**
	 * 原始ID
	 */
	@Column
	@NotNull
	private String wechatId;

	/**
	 * 公众号类型
	 * 
	 * @formtype enum
	 */
	@Column
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	private Type type;

	/**
	 * 公众号Token
	 */
	@Column
	@NotNull
	private String token;
	/**
	 * 消息加密
	 */
	@Column
	// @Size(min = 43, max = 43)
	private String encodingAesKey;
	/**
	 * 联系邮箱
	 */
	@Column
	private String email;
	/**
	 * 公众号描述
	 * 
	 * @formtype textarea
	 */
	@Column(length = 500)
	private String description;
	/**
	 * 应用ID
	 */
	@Column
	@NotNull
	private String appId;
	/**
	 * 应用密钥
	 */
	@Column
	@NotNull
	private String appSecret;
	/**
	 * 图标
	 * 
	 * @formtype image
	 */
	@Column
	private String icon;

	public static enum Type {
		Subscribe("订阅号"), Service("服务号"), Enterprise("企业号");

		private String label;

		Type(String label) {
			this.label = label;
		}

		public String getLable() {
			return this.label;
		}
	}
}
