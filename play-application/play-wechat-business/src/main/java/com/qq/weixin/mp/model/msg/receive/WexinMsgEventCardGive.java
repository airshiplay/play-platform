package com.qq.weixin.mp.model.msg.receive;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 卡券事件推送
 * <p>
 * 转赠事件推送 <Event> <![CDATA[user_gifting_card]]> </Event>
 * 
 * @author lig
 *
 */
@Root(name = "xml")
public class WexinMsgEventCardGive extends WexinMsgFactory.WechatMsgParent {

	@Element(name = "CardId", data = true)
	private String cardId;

	@Element(name = "UserCardCode", data = true)
	private String userCardCode;
	@Element(name = "IsReturnBack")
	private int isReturnBack;
	@Element(name = "FriendUserName", data = true)
	private String friendUserName;
	@Element(name = "IsChatRoom")
	private int isChatRoom;
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
	public int getIsReturnBack() {
		return isReturnBack;
	}
	public void setIsReturnBack(int isReturnBack) {
		this.isReturnBack = isReturnBack;
	}
	public String getFriendUserName() {
		return friendUserName;
	}
	public void setFriendUserName(String friendUserName) {
		this.friendUserName = friendUserName;
	}
	public int getIsChatRoom() {
		return isChatRoom;
	}
	public void setIsChatRoom(int isChatRoom) {
		this.isChatRoom = isChatRoom;
	}
	
	
	
}
