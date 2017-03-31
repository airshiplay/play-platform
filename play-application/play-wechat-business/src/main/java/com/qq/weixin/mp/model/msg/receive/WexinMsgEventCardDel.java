package com.qq.weixin.mp.model.msg.receive;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 卡券事件推送
 * <p>
 * 删除事件推送 <MsgType><![CDATA[user_del_card]]></MsgType>
 * 
 * @author lig
 *
 */
@Root(name = "xml")
public class WexinMsgEventCardDel extends WexinMsgFactory.WechatMsgParent {
	@Element(name = "CardId", data = true)
	private String cardId;

	@Element(name = "UserCardCode", data = true)
	private String userCardCode;

	@Override
	public String getEvent() {
		return super.getEvent();
	}
}
