package com.qq.weixin.mp.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.qq.weixin.mp.model.pay.PayCloseorderReq;
import com.qq.weixin.mp.model.pay.PayCloseorderResp;
import com.qq.weixin.mp.model.pay.PayOrderqueryReq;
import com.qq.weixin.mp.model.pay.PayRefundReq;
import com.qq.weixin.mp.model.pay.PayRefundqueryReq;
import com.qq.weixin.mp.model.pay.PayUnifiedorderReq;
import com.qq.weixin.mp.model.pay.PayUnifiedorderResp;

public interface WexinApiPay   {
	public static final String baseUrl = "https://api.mch.weixin.qq.com/";

	/**
	 * 统一下单接口
	 * 
	 * @param req
	 * @return
	 */
	@POST("pay/unifiedorder")
	public Call<PayUnifiedorderResp> unifiedorder(@Body PayUnifiedorderReq req);

	/**
	 * 查询订单
	 * 
	 * <pre>
	 * 该接口提供所有微信支付订单的查询，商户可以通过查询订单接口主动查询订单状态，完成下一步的业务逻辑。
	 * 
	 * 需要调用查询接口的情况：
	 * ◆ 当商户后台、网络、服务器等出现异常，商户系统最终未接收到支付通知；
	 * ◆ 调用支付接口后，返回系统错误或未知交易状态情况；
	 * ◆ 调用被扫支付API，返回USERPAYING的状态；
	 * ◆ 调用关单或撤销接口API之前，需确认支付状态；
	 * </pre>
	 * 
	 * @param req
	 * @return 返回值存在不定key（代金券相关数据），需自己解析........
	 */
	@POST("pay/orderquery")
	public Call<String> orderquery(@Body PayOrderqueryReq req);

	/**
	 * 关闭订单
	 * 
	 * <pre>
	 * 以下情况需要调用关单接口：商户订单支付失败需要生成新单号重新发起支付，要对原订单号调用关单，避免重复支付；系统下单后，用户支付超时，系统退出不再受理，避免用户继续，请调用关单接口。
	 * <b>注意：订单生成后不能马上调用关单接口，最短调用时间间隔为5分钟。</b>
	 * </pre>
	 * 
	 * @param req
	 * @return
	 */
	@POST("pay/closeorder")
	public Call<PayCloseorderResp> closeorder(@Body PayCloseorderReq req);

	/**
	 * 申请退款
	 * 
	 * <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4">
	 * link</a>.
	 * 
	 * <pre>
	 * 当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
	 * 注意：
	 * 1、交易时间超过一年的订单无法提交退款；
	 * 2、微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。一笔退款失败后重新提交，要采用原来的退款单号。总退款金额不能超过用户实际支付金额。
	 * 
	 * 是否需要证书
	 * 需要。
	 * 
	 * </pre>
	 * 
	 * @param req
	 * 
	 * @return 返回值存在不定key（代金券相关数据），需自己解析........
	 */
	@POST("secapi/pay/refund")
	public Call<String> refund(@Body PayRefundReq req);

	/**
	 * 查询退款
	 * 
	 * <pre>
	 * 应用场景
	 * 提交退款申请后，通过调用该接口查询退款状态。退款有一定延时，用零钱支付的退款20分钟内到账，银行卡支付的退款3个工作日后重新查询退款状态。
	 * 
	 * 接口地址
	 * 接口链接：https://api.mch.weixin.qq.com/pay/refundquery
	 * 
	 * 是否需要证书
	 * 不需要。
	 * 
	 * 
	 * </pre>
	 * 
	 * @param req
	 * @return 返回值存在不定key（代金券相关数据），需自己解析........
	 */

	@POST("pay/refundquery")
	public Call<String> refundquery(@Body PayRefundqueryReq req);

	 
}
