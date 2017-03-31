package com.qq.weixin.mp.model.msg.receive;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 卡券事件推送
 * <p>
 * 审核事件推送 <Event><![CDATA[card_pass_check]]></Event> //不通过为card_not_pass_check
 * 
 * @author lig
 *
 */
@Root(name = "xml")
public class WexinMsgEventCard extends WexinMsgFactory.WechatMsgParent {

	@Element(name = "CardId", data = true)
	private String cardId;
	@Element(name = "RefuseReason", data = true)
	private String refuseReason;
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getRefuseReason() {
		return refuseReason;
	}
	public void setRefuseReason(String refuseReason) {
		this.refuseReason = refuseReason;
	}

}
