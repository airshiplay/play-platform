package com.qq.weixin.mp.model.user;

import java.util.ArrayList;
import java.util.List;

public class WexinUserInfoBatchReq {

	private List<Openid> user_list;

	public static class Openid {
		private String openid;
		private String lang = "zh-CN";

		public Openid() {
		}

		public Openid(String openid) {
			this.openid = openid;
		}

		public Openid(String openid, String lang) {
			this.openid = openid;
			this.lang = lang;
		}

		public String getOpenid() {
			return openid;
		}

		public void setOpenid(String openid) {
			this.openid = openid;
		}

		public String getLang() {
			return lang;
		}

		public void setLang(String lang) {
			this.lang = lang;
		}

	}

	public List<Openid> getUser_list() {
		return user_list;
	}

	public void addOpenid(String openid) {
		if (user_list == null) {
			user_list = new ArrayList<WexinUserInfoBatchReq.Openid>();
		}
		user_list.add(new WexinUserInfoBatchReq.Openid(openid));
	}

	public void addOpenids(String... openids) {
		if (user_list == null) {
			user_list = new ArrayList<WexinUserInfoBatchReq.Openid>();
		}
		for (String openid : openids) {
			user_list.add(new WexinUserInfoBatchReq.Openid(openid));
		}
	}

	public void addOpenids(String lang, String... openids) {
		if (user_list == null) {
			user_list = new ArrayList<WexinUserInfoBatchReq.Openid>();
		}
		for (String openid : openids) {
			user_list.add(new WexinUserInfoBatchReq.Openid(openid, lang));
		}
	}

	public void setUser_list(List<Openid> user_list) {
		this.user_list = user_list;
	}
}
