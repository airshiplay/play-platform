package com.qq.weixin.mp.model.user;

public class WexinUserRemark {
	private String openid;
	private String remark;

	public WexinUserRemark(String openid, String remark) {
		super();
		this.openid = openid;
		this.remark = remark;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
