package com.qq.weixin.mp.model.user;

import java.util.List;

public class WexinUserInfoBatchResp {
	private int errcode;
	private String errmsg;
	private List<WexinUserInfoResp> user_info_list;

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

	public List<WexinUserInfoResp> getUser_info_list() {
		return user_info_list;
	}

	public void setUser_info_list(List<WexinUserInfoResp> user_info_list) {
		this.user_info_list = user_info_list;
	}

}
