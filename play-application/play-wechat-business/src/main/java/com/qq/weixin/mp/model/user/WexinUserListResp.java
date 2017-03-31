package com.qq.weixin.mp.model.user;

import java.util.List;

public class WexinUserListResp {
	private int errcode;
	private String errmsg;
	private Long total;
	private int count;
	private OpenId data;
	private String next_openid;

	public static class OpenId {
		private List<String> openid;

		public List<String> getOpenid() {
			return openid;
		}

		public void setOpenid(List<String> openid) {
			this.openid = openid;
		}

	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public OpenId getData() {
		return data;
	}

	public void setData(OpenId data) {
		this.data = data;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

}
