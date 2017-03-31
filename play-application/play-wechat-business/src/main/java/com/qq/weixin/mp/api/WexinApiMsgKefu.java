package com.qq.weixin.mp.api;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import com.qq.weixin.mp.model.WexinResp;
import com.qq.weixin.mp.model.msg.send.kf.WexinKfListResp;
import com.qq.weixin.mp.model.msg.send.kf.WexinKfMsgReq;

/**
 * 发送消息-- 客服消息
 * 
 * @author lig
 *
 */
public interface WexinApiMsgKefu {
	public static final String baseUrl = "https://api.weixin.qq.com/customservice/";

	@GET("getkflist")
	@Headers("access_token: 0")
	Call<WexinKfListResp> getkflist();

	@POST("https://api.weixin.qq.com/cgi-bin/message/custom/send")
	@Headers("access_token: 0")
	Call<WexinResp> sendMsg(@Body WexinKfMsgReq req);

}
