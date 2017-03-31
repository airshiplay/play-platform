package com.airshiplay.play.wechat.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

/**
 * 微信客服
 * 
 * @author lig
 *
 */
@Getter
@Setter
@Entity
@Table(name = "wechat_kf")
public class WechatKfEntity extends DataEntity<AdminUserEntity, Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "kf_account")
	private String kfAccount;

	@Column(name = "kf_nick")
	private String kfNick;

	@Column(name = "kf_id")
	private String kfId;

	@Column(name = "kf_headimgurl")
	private String kfHeadImageUrl;
}
