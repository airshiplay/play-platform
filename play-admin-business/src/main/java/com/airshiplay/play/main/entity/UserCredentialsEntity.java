package com.airshiplay.play.main.entity;

import com.airlenet.repo.jpa.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sys_user_credentials")
public class UserCredentialsEntity extends BaseEntity<Long> {

	private static final long serialVersionUID = -2146596304920130358L;

	@NotNull
	@Column(length = 50)
	private String type;

	@Column(length = 200)
	private String account;

	@NotNull
	@Column(length = 200)
	private String secret;

	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	private AdminUserEntity user;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public AdminUserEntity getUser() {
		return user;
	}

	public void setUser(AdminUserEntity user) {
		this.user = user;
	}

}
