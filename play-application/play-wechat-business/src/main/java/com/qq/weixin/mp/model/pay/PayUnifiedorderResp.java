package com.qq.weixin.mp.model.pay;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "xml")
public class PayUnifiedorderResp {
	@Element(data = true)
	private String return_code;
	@Element(data = true)
	private String return_msg;
	@Element(data = true)
	private String appid;
	@Element(data = true)
	private String mch_id;
	@Element(data = true)
	private String nonce_str;
	@Element(data = true)
	private String sign;
	@Element(data = true)
	private String result_code;
	@Element(data = true)
	private String prepay_id;
	@Element(data = true)
	private String trade_type;
}
