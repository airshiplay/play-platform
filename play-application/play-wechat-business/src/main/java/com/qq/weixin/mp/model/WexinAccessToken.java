package com.qq.weixin.mp.model;

import java.util.Date;

public class WexinAccessToken {
	/**
	 * 获取到的凭证
	 */
	private String access_token;
	/**
	 * 凭证有效时间，单位：秒
	 */
	private int expires_in;
	private int errcode;
	private String errmsg;
	private Long startTime = System.currentTimeMillis();

	public String getAccess_token() {
		return access_token;
	}

	/**
	 * 凭证是否过期
	 * 
	 * @return
	 */
	public boolean isExpires() {
		return (System.currentTimeMillis() - startTime) / 1000 - expires_in + 5 > 0;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
