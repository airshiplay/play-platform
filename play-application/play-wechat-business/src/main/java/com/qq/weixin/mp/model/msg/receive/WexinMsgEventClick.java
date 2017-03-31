package com.qq.weixin.mp.model.msg.receive;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * <pre>
 * <b>点击菜单拉取消息时的事件推送</b>
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[FromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[CLICK]]></Event>
 * <EventKey><![CDATA[EVENTKEY]]></EventKey>
 * </xml>
 * 
 * </pre>
 * 
 * @author lig
 *
 */
@Root(name = "xml")
public class WexinMsgEventClick extends WexinMsgFactory.WechatMsgParent {
	@Element(name = "EventKey", required = false)
	private String eventKey;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
