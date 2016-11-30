package com.qq.weixin;

public class WexinConfig {
	private String appid;
	private String secret;
	private String grantType;
	private String mchid;

	public WexinConfig() {

	}

	public WexinConfig(String appid, String secret) {
		this.grantType = "client_credential";
		this.appid = appid;
		this.secret = secret;
	}

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMchid(String mchid) {
		this.mchid = mchid;
	}

}
