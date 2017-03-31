package com.qq.weixin.mp.model.msg.receive;

import org.simpleframework.xml.Element;

/**
 * 卡券事件推送
 * <p>
 * 核销事件推送<Event> <![CDATA[user_consume_card]]> </Event>
 * 
 * @author lig
 *
 */
public class WexinMsgEventCardCancel extends WexinMsgFactory.WechatMsgParent {
	@Element(name = "CardId", data = true)
	private String cardId;

	@Element(name = "UserCardCode", data = true)
	private String userCardCode;
	@Element(name = "ConsumeSource", data = true)
	private String consumeSource;
	@Element(name = "LocationName", data = true)
	private String locationName;
	@Element(name = "StaffOpenId", data = true)
	private String staffOpenId;
	@Element(name = "VerifyCode", data = true)
	private String verifyCode;
	@Element(name = "RemarkAmount", data = true)
	private String remarkAmount;
	@Element(name = "OuterStr", data = true)
	private String outerStr;
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getUserCardCode() {
		return userCardCode;
	}
	public void setUserCardCode(String userCardCode) {
		this.userCardCode = userCardCode;
	}
	public String getConsumeSource() {
		return consumeSource;
	}
	public void setConsumeSource(String consumeSource) {
		this.consumeSource = consumeSource;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public String getStaffOpenId() {
		return staffOpenId;
	}
	public void setStaffOpenId(String staffOpenId) {
		this.staffOpenId = staffOpenId;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public String getRemarkAmount() {
		return remarkAmount;
	}
	public void setRemarkAmount(String remarkAmount) {
		this.remarkAmount = remarkAmount;
	}
	public String getOuterStr() {
		return outerStr;
	}
	public void setOuterStr(String outerStr) {
		this.outerStr = outerStr;
	}

}
