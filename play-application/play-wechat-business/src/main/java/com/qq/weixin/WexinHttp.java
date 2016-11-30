package com.qq.weixin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.HashMap;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

import com.qq.weixin.mp.api.WexinBasicApi;
import com.qq.weixin.mp.model.WexinAccessToken;
import com.qq.weixin.mp.model.WexinTicket;

public class WexinHttp {
	private static Retrofit retrofit;
	private static WexinConfig config;
	private static OkHttpClient client;
	private static OkHttpClient sslClient;
	private static WexinAccessToken accessToken;
	private static WexinTicket wechatTicket;
	private static HashMap<String, Retrofit> retrofitMap = new HashMap<String, Retrofit>();
	static {
		client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {

			@Override
			public Response intercept(Chain chain) throws IOException {
				Request oldRequest = chain.request();

				if (oldRequest.headers("access_token").isEmpty()) {
					return chain.proceed(chain.request());
				}
				try {
					initAccessToken();
				} catch (ServiceException e) {
					e.printStackTrace();
				}
				// 添加新的参数
				HttpUrl.Builder authorizedUrlBuilder = oldRequest
						.url()
						.newBuilder()
						.scheme(oldRequest.url().scheme())
						.host(oldRequest.url().host())
						.addQueryParameter("access_token",
								accessToken.getAccess_token());
				// 新的请求
				Request newRequest = oldRequest.newBuilder()
						.method(oldRequest.method(), oldRequest.body())
						.url(authorizedUrlBuilder.build()).build();

				return chain.proceed(newRequest);
			}
		}).addInterceptor(new Interceptor() {
			Logger logger = LoggerFactory.getLogger("okhttplog");

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

				return response.newBuilder()
						.body(okhttp3.ResponseBody.create(mediaType, content))
						.build();
			}
		}).build();

		retrofit = new Retrofit.Builder().client(client)
				.baseUrl(WexinBasicApi.baseUrl)
				.addConverterFactory(GsonConverterFactory.create()).build();
		retrofitMap.put(WexinBasicApi.baseUrl, retrofit);
		config = new WexinConfig();
		config.setGrantType("client_credential");
		config.setAppid("wx2ab7d2c9e9640c8b");
		config.setSecret("15be487a1ac6f3d6aea6c39b88cc35df");
	}

	public static void initAccessToken() throws IOException, ServiceException {
		if (accessToken != null && !accessToken.isExpires()) {
			return;
		}
		WexinBasicApi basicApi = getApi(WexinBasicApi.baseUrl,
				WexinBasicApi.class);

		Call<WexinAccessToken> call = basicApi.token(config.getGrantType(),
				config.getAppid(), config.getSecret());
		accessToken = call.execute().body();
		if (accessToken.getErrcode() != 0) {
			throw new ServiceException(accessToken.getErrmsg());
		}

	}

	public static WexinTicket getWechatTicket() throws IOException,
			ServiceException {
		if (wechatTicket != null && !wechatTicket.isExpires()) {
			return wechatTicket;
		}
		initAccessToken();

		WexinBasicApi basicApi = getApi(WexinBasicApi.baseUrl,
				WexinBasicApi.class);

		Call<WexinTicket> call = basicApi.getTicket("jsapi");
		wechatTicket = call.execute().body();
		if (wechatTicket.getErrcode() != 0) {
			throw new ServiceException(wechatTicket.getErrmsg());
		}
		return wechatTicket;
	}

	public synchronized static <T> T getApi(Class<T> t) {
		try {
			initAccessToken();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return retrofit.create(t);
	}

	public synchronized static <T> T getApi(String baseUrl, Class<T> t) {
		Retrofit retrofit = retrofitMap.get(baseUrl);
		if (retrofit == null) {
			retrofit = new Retrofit.Builder().client(client).baseUrl(baseUrl)
					.addConverterFactory(GsonConverterFactory.create()).build();
			retrofitMap.put(baseUrl, retrofit);
		}
		return retrofit.create(t);
	}

	public synchronized static <T> T getXmlApi(String baseUrl, Class<T> t) {
		Retrofit retrofit = retrofitMap.get(baseUrl);
		if (retrofit == null) {
			retrofit = new Retrofit.Builder().client(client).baseUrl(baseUrl)
					.addConverterFactory(SimpleXmlConverterFactory.create())
					.build();
			retrofitMap.put(baseUrl, retrofit);
		}
		return retrofit.create(t);
	}

	public synchronized static <T> T getSSLXmlApi(String baseUrl, Class<T> t) {
		Retrofit retrofit = retrofitMap.get(baseUrl + "ssl");
		if (retrofit == null) {
			retrofit = new Retrofit.Builder().client(sslClient)
					.baseUrl(baseUrl)
					.addConverterFactory(SimpleXmlConverterFactory.create())
					.build();
			retrofitMap.put(baseUrl + "ssl", retrofit);
		}
		return retrofit.create(t);
	}

	public void sslClient(String p12path, String password)
			throws KeyStoreException, NoSuchAlgorithmException,
			CertificateException, IOException, KeyManagementException,
			UnrecoverableKeyException {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File(p12path));
		try {
			keyStore.load(instream, password.toCharArray());
		} finally {
			instream.close();
		}
		// ///////////////////////////////////////

		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(keyStore);
		TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
		if (trustManagers.length != 1
				|| !(trustManagers[0] instanceof X509TrustManager)) {
			throw new IllegalStateException(
					"Unexpected default trust managers:"
							+ Arrays.toString(trustManagers));
		}
		X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

		SSLContext sslContext = SSLContext.getInstance("TLSv1");
		sslContext.init(null, new TrustManager[] { trustManager }, null);
		SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
		sslClient = new OkHttpClient.Builder().sslSocketFactory(
				sslSocketFactory, trustManager).build();
	}

	public static WexinConfig getDefaultWechatConfig() {
		return config;
	}

	public static void setDefaultWechatConfig(WexinConfig c) {
		config = c;
	}
}
