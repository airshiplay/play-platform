package com.qq.weixin.mp.model.msg;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.qq.weixin.mp.model.msg.WexinMsgFactory;

@Root(name = "xml")
public class WexinLocationMsg extends WexinMsgFactory.WechatMsgParent {

	@Element(name = "Event", data = true)
	private String event;
	@Element(name = "Latitude")
	private String latitude;
	@Element(name = "Longitude")
	private String longitude;
	@Element(name = "Precision")
	private String precision;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

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
