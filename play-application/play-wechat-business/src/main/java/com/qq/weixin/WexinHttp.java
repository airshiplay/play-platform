package com.qq.weixin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
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

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class WexinHttp {
	private static HashMap<String, Retrofit> retrofitMap = new HashMap<String, Retrofit>();

	public synchronized static <T> T getApi(String appid, String appSecret, Class<T> cls) {
		String baseUrl = null;

		Field[] fields = cls.getFields();
		for (Field field : fields) {
			if (field.getName().equalsIgnoreCase("baseurl")) {
				try {
					baseUrl = field.get(null).toString();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		Retrofit retrofit = retrofitMap.get(appid + baseUrl);
		if (retrofit == null) {
			retrofit = new Retrofit.Builder().client(getOkHttpClient(appid, appSecret)).baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
			retrofitMap.put(appid + baseUrl, retrofit);
		}
		return retrofit.create(cls);
	}

	public synchronized static <T> T getXmlApi(String appid, String appSecret, Class<T> cls) {
		Retrofit retrofit = retrofitMap.get(appid);
		if (retrofit == null) {
			String baseUrl = null;

			Field[] fields = cls.getFields();
			for (Field field : fields) {
				if (field.getName().equalsIgnoreCase("baseurl")) {
					try {
						baseUrl = field.get(null).toString();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					break;
				}
			}
			retrofit = new Retrofit.Builder().client(getOkHttpClient(appid, appSecret)).baseUrl(baseUrl).addConverterFactory(SimpleXmlConverterFactory.create()).build();
			retrofitMap.put(appid, retrofit);
		}
		return retrofit.create(cls);
	}

	public synchronized static <T> T getSSLXmlApi(String appid, String appSecret, String p12path, String password, Class<T> cls) throws KeyManagementException, UnrecoverableKeyException,
			KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException {
		Retrofit retrofit = retrofitMap.get(appid + "ssl");
		if (retrofit == null) {
			String baseUrl = null;

			Field[] fields = cls.getFields();
			for (Field field : fields) {
				if (field.getName().equalsIgnoreCase("baseurl")) {
					try {
						baseUrl = field.get(null).toString();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					break;
				}
			}
			retrofit = new Retrofit.Builder().client(getSslClient(appid, appSecret, p12path, password)).baseUrl(baseUrl).addConverterFactory(SimpleXmlConverterFactory.create()).build();
			retrofitMap.put(appid + "ssl", retrofit);
		}
		return retrofit.create(cls);
	}

	private static OkHttpClient getOkHttpClient(String appid, String appSecret) {
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);
		return new OkHttpClient.Builder().addInterceptor(logging).addInterceptor(new AccessTokenInterceptor(appid, appSecret)).addInterceptor(new LogInterceptor()).build();
	}

	private static OkHttpClient getSslClient(String appid, String appSecret, String p12path, String password) throws KeyStoreException, NoSuchAlgorithmException, CertificateException, IOException,
			KeyManagementException, UnrecoverableKeyException {
		KeyStore keyStore = KeyStore.getInstance("PKCS12");
		FileInputStream instream = new FileInputStream(new File(p12path));
		try {
			keyStore.load(instream, password.toCharArray());
		} finally {
			instream.close();
		}
		// ///////////////////////////////////////

		TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		trustManagerFactory.init(keyStore);
		TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
		if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
			throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
		}
		X509TrustManager trustManager = (X509TrustManager) trustManagers[0];

		SSLContext sslContext = SSLContext.getInstance("TLSv1");
		sslContext.init(null, new TrustManager[] { trustManager }, null);
		SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
		return new OkHttpClient.Builder().addInterceptor(new AccessTokenInterceptor(appid, appSecret)).addInterceptor(new LogInterceptor()).sslSocketFactory(sslSocketFactory, trustManager).build();
	}

}
