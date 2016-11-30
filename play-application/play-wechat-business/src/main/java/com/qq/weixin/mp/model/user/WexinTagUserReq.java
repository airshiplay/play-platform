package com.qq.weixin.mp.model.user;

public class WexinTagUserReq {
	private int tagid;
	private String next_openid;

	public WexinTagUserReq(int tagid) {
		this.tagid = tagid;
	}

	public WexinTagUserReq(int tagid, String next_openid) {
		this.tagid = tagid;
		this.next_openid = next_openid;
	}

	public int getTagid() {
		return tagid;
	}

	public void setTagid(int tagid) {
		this.tagid = tagid;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}

}
