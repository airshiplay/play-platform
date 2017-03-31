package com.qq.weixin.mp.model.msg.receive;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.qq.weixin.mp.model.msg.receive.WexinMsgFactory;

/**
 * 上报地理位置事件
 * 
 * <pre>
 * <xml>
 * <ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>123456789</CreateTime>
 * <MsgType><![CDATA[event]]></MsgType>
 * <Event><![CDATA[LOCATION]]></Event>
 * <Latitude>23.137466</Latitude>
 * <Longitude>113.352425</Longitude>
 * <Precision>119.385040</Precision>
 * </xml>
 * </pre>
 * 
 * @author lig
 *
 */
@Root(name = "xml")
public class WexinMsgEventLocation extends WexinMsgFactory.WechatMsgParent {

	@Element(name = "Latitude")
	private String latitude;
	@Element(name = "Longitude")
	private String longitude;
	@Element(name = "Precision")
	private String precision;

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

}
