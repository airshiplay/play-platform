package com.qq.weixin.mp.model.pay;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "xml")
public class PayCloseorderResp {
	@Element
	private String return_code;
	@Element
	private String return_msg;
	// ////以下字段在return_code为SUCCESS的时候有返回
	@Element
	private String appid;
	@Element
	private String mch_id;
	@Element
	private String nonce_str;
	@Element
	private String sign;
	/**
	 * 业务结果 result_code 是 String(16) SUCCESS SUCCESS/FAIL
	 */
	@Element
	private String result_code;
	/**
	 * 业务结果描述 result_msg 是 String(32) OK 对于业务执行的详细描述
	 */
	@Element
	private String result_msg;
	/**
	 * 错误代码 err_code 否 String(32) SYSTEMERROR 详细参见第6节错误列表
	 */
	@Element
	private String err_code;
	/**
	 * 错误代码描述 err_code_des 否 String(128) 系统错误 结果信息描述
	 */
	@Element
	private String err_code_des;
}
