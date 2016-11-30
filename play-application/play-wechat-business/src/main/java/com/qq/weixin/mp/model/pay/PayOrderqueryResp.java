package com.qq.weixin.mp.model.pay;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "xml")
public class PayOrderqueryResp {

	@Element
	private String return_code;
	// ///以下字段在return_code为SUCCESS的时候有返回
	@Element
	private String return_msg;
	@Element
	private String appid;
	@Element
	private String mch_id;
	@Element
	private String nonce_str;
	@Element
	private String sign;
	@Element
	private String result_code;
	@Element
	private String err_code;
	@Element
	private String err_code_des;
	@Element
	// ////////以下字段在return_code 和result_code都为SUCCESS的时候有返回
	private String device_info;
	@Element
	private String openid;
	@Element
	private String is_subscribe;
	@Element
	private String trade_type;
	@Element
	private String trade_state;
	@Element
	private String bank_type;
	@Element
	private int total_fee;
	@Element
	private int settlement_total_fee;
	@Element
	private String fee_type;
	@Element
	private int cash_fee;
	@Element
	private String cash_fee_type;
	@Element
	private int coupon_fee;
	@Element
	private int coupon_count;
	@Element
	private String coupon_batch_id_$n;
	@Element
	private int coupon_type_$n;
	@Element
	private String coupon_id_$n;
	@Element
	private int coupon_fee_$n;
	@Element
	private String transaction_id;
	@Element
	private String out_trade_no;
	@Element
	private String attach;
	/**
	 * 支付完成时间 time_end 是 String(14) 20141030133525
	 * 订单支付时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
	 */
	@Element
	private String time_end;
	/**
	 * 交易状态描述 trade_state_desc 是 String(256) 支付失败，请重新下单支付 对当前查询订单状态的描述和下一步操作的指引
	 */
	@Element
	private String trade_state_desc;

}
