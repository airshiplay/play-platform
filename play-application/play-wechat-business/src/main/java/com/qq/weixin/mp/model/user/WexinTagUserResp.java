package com.qq.weixin.mp.model.user;

import java.util.List;

public class WexinTagUserResp {
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

}
