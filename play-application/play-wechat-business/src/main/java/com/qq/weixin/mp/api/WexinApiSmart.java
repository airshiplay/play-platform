package com.qq.weixin.mp.api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

import com.qq.weixin.mp.model.smart.WexinSemproxyReq;

public interface WexinApiSmart   {
	public static final String baseUrl = "https://api.weixin.qq.com/";

	/**
	 * 语义理解
	 * 
	 * @param req
	 * @return
	 */
	@POST("semantic/semproxy/search")
	@Headers("access_token: 0")
	Call<Map<String, Object>> semproxySearch(@Body WexinSemproxyReq req);
 
}
