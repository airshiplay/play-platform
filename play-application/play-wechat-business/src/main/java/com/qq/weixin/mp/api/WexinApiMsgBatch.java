package com.qq.weixin.mp.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import com.qq.weixin.mp.model.WexinResp;
import com.qq.weixin.mp.model.msg.send.batch.WexinSendMsgAllReq;

/**
 * 发送消息-- 群发消息
 * 
 * @author lig
 *
 */
public interface WexinApiMsgBatch {
	public static final String baseUrl = "https://api.weixin.qq.com/cgi-bin/message/";

	/**
	 * 根据标签进行群发【订阅号与服务号认证后均可用】
	 * 
	 * @param req
	 * @return
	 */
	@POST("mass/sendall")
	@Headers("access_token: 0")
	Call<WexinResp> sendAllMsg(@Body WexinSendMsgAllReq req);

}
