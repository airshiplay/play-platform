package com.qq.weixin.mp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import com.qq.weixin.mp.model.WexinAccessToken;
import com.qq.weixin.mp.model.WexinIpList;
import com.qq.weixin.mp.model.WexinTicket;

public interface WexinBasicApi {
	public static final String baseUrl = "https://api.weixin.qq.com/cgi-bin/";

	@GET("token")
	Call<WexinAccessToken> token(@Query("grant_type") String grantType,
			@Query("appid") String appid, @Query("secret") String secret);

	@GET("getcallbackip")
	@Headers("access_token: 0")
	Call<WexinIpList> getCallbackIp();

	/**
	 * 
	 * @param type
	 *            =jsapi
	 * @return
	 */
	@GET("ticket/getticket")
	@Headers("access_token: 0")
	Call<WexinTicket> getTicket(@Query("type") String type);

}
