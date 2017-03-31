package com.qq.weixin;

import java.io.IOException;

import com.qq.weixin.mp.api.WexinApiBasic;
import com.qq.weixin.mp.model.WexinAccessToken;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class AccessTokenInterceptor implements Interceptor {
	private WexinConfig config;
	private WexinAccessToken accessToken;

	public AccessTokenInterceptor(String appid, String appSecret) {
		config = new WexinConfig(appid, appSecret);
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request oldRequest = chain.request();

		if (oldRequest.headers("access_token").isEmpty()) {
			return chain.proceed(chain.request());
		}
		try {
			initAccessToken();
		} catch (WechatServiceException e) {
			e.printStackTrace();
		}
		// 添加新的参数
		HttpUrl.Builder authorizedUrlBuilder = oldRequest.url().newBuilder().scheme(oldRequest.url().scheme()).host(oldRequest.url().host())
				.addQueryParameter("access_token", accessToken.getAccess_token());
		// 新的请求
		Request newRequest = oldRequest.newBuilder().method(oldRequest.method(), oldRequest.body()).url(authorizedUrlBuilder.build()).build();

		return chain.proceed(newRequest);
	}

	public void initAccessToken() throws IOException, WechatServiceException {
		if (accessToken != null && !accessToken.isExpires()) {
			return;
		}
		WexinApiBasic basicApi = WexinHttp.getApi(config.getAppid(), config.getSecret(), WexinApiBasic.class);

		Call<WexinAccessToken> call = basicApi.token(config.getGrantType(), config.getAppid(), config.getSecret());
		accessToken = call.execute().body();
		if (accessToken.getErrcode() != 0) {
			throw new WechatServiceException(accessToken.getErrcode(), accessToken.getErrmsg());
		}

	}
}
