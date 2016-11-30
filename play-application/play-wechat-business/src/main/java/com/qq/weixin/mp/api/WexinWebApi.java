package com.qq.weixin.mp.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import com.qq.weixin.mp.model.user.WexinUserInfoResp;
import com.qq.weixin.mp.model.web.WexinUserAccessToken;

public interface WexinWebApi {
	public static final String baseUrl = "https://api.weixin.qq.com/";

	/**
	 * 通过code换取网页授权access_token
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @param grant_type
	 *            authorization_code
	 * @return
	 */
	@GET("sns/oauth2/access_token")
	Call<WexinUserAccessToken> userToken(@Query("appid") String appid,
			@Query("secret") String secret, @Query("code") String code,
			@Query("grant_type") String grant_type);

	/**
	 * 刷新access_token（如果需要）
	 * 
	 * @param appid
	 * @param grant_type
	 *            refresh_token
	 * @param refresh_token
	 * @return
	 */
	@GET("sns/oauth2/refresh_token")
	Call<WexinUserAccessToken> refresh_token(@Query("appid") String appid,
			@Query("grant_type") String grant_type,
			@Query("refresh_token") String refresh_token);

	/**
	 * 拉取用户信息(需scope为 snsapi_userinfo)
	 * 
	 * @param access_token
	 *            为网页授权获取的access_token
	 * @param openid
	 * @param lang
	 *            zh_CN
	 * @return
	 */
	@GET("sns/userinfo")
	Call<WexinUserInfoResp> getUserinfo(
			@Query("access_token") String access_token,
			@Query("openid") String openid, @Query("lang") String lang);

	/**
	 * 检验授权凭证（access_token）是否有效
	 * 
	 * @param access_token
	 *            网页授权接口调用凭证,注意：此access_token与基础支持的access_token不同
	 * 
	 * @param openid
	 *            用户的唯一标识
	 * 
	 * @return
	 */

	@GET("sns/auth")
	Call<String> checkToken(@Query("access_token") String access_token,
			@Query("openid") String openid);

}
