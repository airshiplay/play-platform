package com.qq.weixin.mp.model;

public class WexinTicket {
	private int errcode;
	private String errmsg;
	private String ticket;
	private int expires_in;
	private Long startTime = System.currentTimeMillis();

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

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	/**
	 * 凭证是否过期
	 * 
	 * @return
	 */
	public boolean isExpires() {
		return (System.currentTimeMillis() - startTime) / 1000 - expires_in + 5 > 0;
	}

}
