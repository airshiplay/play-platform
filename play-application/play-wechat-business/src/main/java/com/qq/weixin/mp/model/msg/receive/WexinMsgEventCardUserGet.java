package com.qq.weixin.mp.model.msg.receive;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 卡券事件推送
 * <p>
 * 领取事件推送 <Event> <![CDATA[user_get_card]]> </Event>
 * 
 * @author lig
 *
 */
@Root(name = "xml")
public class WexinMsgEventCardUserGet extends WexinMsgFactory.WechatMsgParent {

	@Element(name = "CardId", data = true)
	private String cardId;

	@Element(name = "IsGiveByFriend")
	private int IsGiveByFriend;
	@Element(name = "UserCardCode", data = true)
	private String userCardCode;
	@Element(name = "FriendUserName", data = true)
	private String friendUserName;
	@Element(name = "OuterId")
	private String outerId;
	@Element(name = "OldUserCardCode", data = true)
	private String oldUserCardCode;
	@Element(name = "OuterStr", data = true)
	private String outerStr;
	@Element(name = "isRestoreMemberCard")
	private int isRestoreMemberCard;
	@Element(name = "IsRecommendByFriend")
	private int isRecommendByFriend;

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public int getIsGiveByFriend() {
		return IsGiveByFriend;
	}

	public void setIsGiveByFriend(int isGiveByFriend) {
		IsGiveByFriend = isGiveByFriend;
	}

	public String getUserCardCode() {
		return userCardCode;
	}

	public void setUserCardCode(String userCardCode) {
		this.userCardCode = userCardCode;
	}

	public String getFriendUserName() {
		return friendUserName;
	}

	public void setFriendUserName(String friendUserName) {
		this.friendUserName = friendUserName;
	}

	public String getOuterId() {
		return outerId;
	}

	public void setOuterId(String outerId) {
		this.outerId = outerId;
	}

	public String getOldUserCardCode() {
		return oldUserCardCode;
	}

	public void setOldUserCardCode(String oldUserCardCode) {
		this.oldUserCardCode = oldUserCardCode;
	}

	public String getOuterStr() {
		return outerStr;
	}

	public void setOuterStr(String outerStr) {
		this.outerStr = outerStr;
	}

	public int getIsRestoreMemberCard() {
		return isRestoreMemberCard;
	}

	public void setIsRestoreMemberCard(int isRestoreMemberCard) {
		this.isRestoreMemberCard = isRestoreMemberCard;
	}

	public int getIsRecommendByFriend() {
		return isRecommendByFriend;
	}

	public void setIsRecommendByFriend(int isRecommendByFriend) {
		this.isRecommendByFriend = isRecommendByFriend;
	}
}
