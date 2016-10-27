package com.airshiplay.play.oauth2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.airshiplay.play.main.entity.UserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

@Entity
@Table(name = "oauth2_access_token")
public class AccessTokenEntity extends DataEntity<UserEntity, Long> {

	private static final long serialVersionUID = 1L;
	public static final String BEARER_TYPE = "Bearer";

	/**
	 * 默认的 refresh_token 的有效时长: 30天
	 */
	public final static int REFRESH_TOKEN_VALIDITY_SECONDS = 60 * 60 * 24 * 30;

	/**
	 * 默认的 access_token 的有效时长: 12小时
	 */
	public final static int ACCESS_TOKEN_VALIDITY_SECONDS = 60 * 60 * 12;

	protected static long THOUSAND = 1000L;

	@Column(name = "token_id")
	private String tokenId;
	@Column
	private String username;

	@Column(name = "client_id")
	private String clientId;

	@Column(name = "authentication_id")
	private String authenticationId;

	@Column(name = "refresh_token")
	private String refreshToken;

	@Column(name = "token_type")
	private String tokenType = BEARER_TYPE;

	@Column(name = "token_expired_seconds")
	private int tokenExpiredSeconds = ACCESS_TOKEN_VALIDITY_SECONDS;

	@Column(name = "refresh_token_expired_seconds")
	private int refreshTokenExpiredSeconds = REFRESH_TOKEN_VALIDITY_SECONDS;

}
