package com.qq.weixin;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogInterceptor implements  Interceptor{
	private static Logger logger = LoggerFactory.getLogger("okhttplog");

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		logger.info(request.toString());
		long t1 = System.nanoTime();
		okhttp3.Response response = chain.proceed(chain.request());
		long t2 = System.nanoTime();
		logger.info("time=" + (t2 - t1) / 1e6d + "s");
		okhttp3.MediaType mediaType = response.body().contentType();
		String content = response.body().string();

		return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
	}
}