package com.qq.weixin.mp.model.pay;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "xml")
public class PayNotifyPayResultReturn {
	@Element(required = true, data = true)
	private String return_code;
	@Element(required = true, data = true)
	private String return_msg;

	public PayNotifyPayResultReturn() {

	}

	public PayNotifyPayResultReturn(String return_code, String return_msg) {
		this.return_code = return_code;
		this.return_msg = return_msg;
	}

	public static PayNotifyPayResultReturn SUCCESS() {
		return new PayNotifyPayResultReturn("SUCCESS", "OK");
	}

	public static PayNotifyPayResultReturn FAIL(String msg) {
		return new PayNotifyPayResultReturn("FAIL", msg);
	}

	public String getReturn_code() {
		return return_code;
	}

	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}

	public String getReturn_msg() {
		return return_msg;
	}

	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}

}
