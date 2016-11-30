package com.qq.weixin.mp.model.pay;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 申请退款-返回对象 See<a
 * href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4"> link</a>
 * 
 * @author airshiplay
 */
@Root(name = "xml")
public class PayRefundResp {
	@Element
	private String return_code;
	@Element
	private String return_msg;
	// /////以下字段在return_code为SUCCESS的时候有返回
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
	private String out_refund_no;
	@Element
	private String refund_id;
	@Element
	private String fefund_channel;
	@Element
	private int fefund_fee;
	@Element
	private int settlement_refund_fee;
	@Element
	private int total_fee;
	@Element
	private int settlement_total_fee;
	@Element
	private String fee_type;
	@Element
	private int cash_fee;
	@Element
	private int cash_refund_fee;
	/**
	 * 代金券类型 coupon_type_$n 否 String(8) CASH CASH--充值代金券 NO_CASH---非充值代金券
	 * 订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0
	 */
	@Element
	private String coupon_type_$n;
	/**
	 * 代金券退款金额 coupon_refund_fee_$n 否 Int 100
	 * 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见<a
	 * href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=12_1"
	 * >代金券或立减优惠<a/>
	 */
	@Element
	private int coupon_refund_fee_$n;
	/**
	 * 退款代金券使用数量 coupon_refund_count_$n 否 Int 1 退款代金券使用数量 ,$n为下标,从0开始编号
	 */
	@Element
	private int coupon_refund_count_$n;
	/**
	 * 退款代金券批次ID coupon_refund_batch_id_$n_$m 否 String(20) 100 退款代金券批次ID
	 * ,$n为下标，$m为下标，从0开始编号
	 */
	@Element
	private String coupon_refund_batch_id_$n_$m;
	/**
	 * 退款代金券ID coupon_refund_id_$n_$m 否 String(20) 10000 退款代金券ID,
	 * $n为下标，$m为下标，从0开始编号
	 */
	@Element
	private String coupon_refund_id_$n_$m;
	/**
	 * 单个退款代金券支付金额 coupon_refund_fee_$n_$m 否 Int 100 单个退款代金券支付金额,
	 * $n为下标，$m为下标，从0开始编号
	 */
	@Element
	private int coupon_refund_fee_$n_$m;
}
