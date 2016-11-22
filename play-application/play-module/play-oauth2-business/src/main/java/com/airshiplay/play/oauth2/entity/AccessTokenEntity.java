package com.airshiplay.play.oauth2.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.DateTime;

import com.airshiplay.play.main.entity.AdminUserEntity;
import com.airshiplay.play.repo.jpa.DataEntity;

@Entity
@Table(name = "oauth2_access_token")
public class AccessTokenEntity extends DataEntity<AdminUserEntity, Long> {

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

	protected static int THOUSAND = 1000;

	@Column(name = "token_id")
	private String tokenId;
	@Column
	@ManyToOne
	private AdminUserEntity user;

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

	public String getTokenId() {
		return tokenId;
	}

	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}

 

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAuthenticationId() {
		return authenticationId;
	}

	public void setAuthenticationId(String authenticationId) {
		this.authenticationId = authenticationId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public int getTokenExpiredSeconds() {
		return tokenExpiredSeconds;
	}

	public void setTokenExpiredSeconds(int tokenExpiredSeconds) {
		this.tokenExpiredSeconds = tokenExpiredSeconds;
	}

	public int getRefreshTokenExpiredSeconds() {
		return refreshTokenExpiredSeconds;
	}

	public void setRefreshTokenExpiredSeconds(int refreshTokenExpiredSeconds) {
		this.refreshTokenExpiredSeconds = refreshTokenExpiredSeconds;
	}

	public AdminUserEntity getUser() {
		return user;
	}

	public void setUser(AdminUserEntity user) {
		this.user = user;
	}
	public boolean tokenExpired() {

		// final long time = getCreatedDate().getTime() +
		// (this.tokenExpiredSeconds * THOUSAND);
		// return time < DateUtils.now().getTime();
		return getCreatedDate()
				.plusSeconds(this.tokenExpiredSeconds * THOUSAND).isBeforeNow();
	}

	public boolean refreshTokenExpired() {
		// final long time = getCreatedDate().getTime() +
		// (this.refreshTokenExpiredSeconds * THOUSAND);
		// return time < DateUtils.now().getTime();

		return getCreatedDate().plusSeconds(
				this.refreshTokenExpiredSeconds * THOUSAND).isBeforeNow();
	}

	public long currentTokenExpiredSeconds() {
		if (tokenExpired()) {
			return -1;
		}
		// final long time = createTime.getTime() + (this.tokenExpiredSeconds *
		// THOUSAND);
		// return (time - DateUtils.now().getTime()) / THOUSAND;
		return getCreatedDate()
				.plusSeconds(this.tokenExpiredSeconds * THOUSAND)
				.getSecondOfMinute()
				- new DateTime().getSecondOfMinute();

	}


}
