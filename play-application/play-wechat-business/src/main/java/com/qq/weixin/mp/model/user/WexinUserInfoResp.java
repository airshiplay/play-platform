package com.qq.weixin.mp.model.user;

import java.util.List;

public class WexinUserInfoResp {
	private int errcode;
	private String errmsg;
	private String remark;
	private String groupid;
	private List<Integer> tagid_list;
	/** 用户是否订阅 */
	private java.lang.Integer subscribe;
	/** 用户的标识 */
	private java.lang.String openid;
	/** 用户的昵称 */
	private java.lang.String nickname;
	/** 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */
	private int sex;
	/** 用户所在城市 */
	private java.lang.String city;
	/** 用户所在国家 */
	private java.lang.String country;
	/** 用户所在省份 */
	private java.lang.String province;
	/** 用户的语言zh_CN */
	private java.lang.String language;
	/** 用户头像 */
	private java.lang.String headimgurl;
	/** 用户关注时间 */
	private java.lang.String subscribe_time;
	/** 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段 */
	private java.lang.String unionid;
	private List<String> privilege;

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

	public int getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public List<Integer> getTagid_list() {
		return tagid_list;
	}

	public void setTagid_list(List<Integer> tagid_list) {
		this.tagid_list = tagid_list;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public java.lang.String getSubscribe_time() {
		return subscribe_time;
	}

	public void setSubscribe_time(java.lang.String subscribe_time) {
		this.subscribe_time = subscribe_time;
	}

	public void setSubscribe(java.lang.Integer subscribe) {
		this.subscribe = subscribe;
	}

	public List<String> getPrivilege() {
		return privilege;
	}

	public void setPrivilege(List<String> privilege) {
		this.privilege = privilege;
	}

}
