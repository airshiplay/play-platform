package com.qq.weixin.mp.model;

import java.util.List;

public class WexinIpList {
	private int errcode;
	private String errmsg;
	private List<String> ip_list;

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

	public List<String> getIp_list() {
		return ip_list;
	}

	public void setIp_list(List<String> ip_list) {
		this.ip_list = ip_list;
	}

}
