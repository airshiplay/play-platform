package com.qq.weixin.mp.model.pay;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "xml")
public class PayRefundqueryResp {

	@Element
	private String return_code;
	@Element
	private String return_msg;

	// //以下字段在return_code为SUCCESS的时候有返回
	@Element
	private String result_code;
	@Element
	private String err_code;
	@Element
	private String err_code_des;
	@Element
	private String appid;
	@Element
	private String mch_id;
	@Element
	private String device_info;
	@Element
	private String nonce_str;
	@Element
	private String sign;
	@Element
	private String transaction_id;
	@Element
	private String out_trade_no;
	@Element
	private int total_fee;
	@Element
	private int settlement_total_fee;
	@Element
	private String fee_type;
	@Element
	private int cash_fee;
	@Element
	private int refund_count;
	@Element
	private String out_refund_no_$n;
	@Element
	private String refund_id_$n;
	@Element
	private String refund_channel_$n;
	@Element
	private int refund_fee_$n;
	@Element
	private int settlement_refund_fee_$n;
	@Element
	private int coupon_type_$n;
	@Element
	private int coupon_refund_fee_$n;
	@Element
	private int coupon_refund_count_$n;
	@Element
	private String coupon_refund_batch_id_$n_$m;
	@Element
	private String coupon_refund_id_$n_$m;
	@Element
	private int coupon_refund_fee_$n_$m;
	@Element
	private String refund_status_$n;
	@Element
	private String refund_recv_accout_$n;

}
