package com.airshiplay.play.http;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * @author airlenet
 * @version 2017-01-15
 */
public class PlayHttpClient {
    private static HashMap<String, Retrofit> retrofitMap = new HashMap<String, Retrofit>();

    static Proxy globalProxy =null;

    public synchronized static <T> T getApi(String baseUrl, Class<T> cls) {
        Retrofit retrofit = retrofitMap.get(baseUrl);
        if (retrofit == null) {
            if (!baseUrl.endsWith("/"))
                baseUrl += "/";
            retrofit = new Retrofit.Builder().client(getOkHttpClient(Proxy.NO_PROXY)).baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create()).build();
            retrofitMap.put(baseUrl, retrofit);
        }
        return retrofit.create(cls);
    }

    public synchronized static <T> T getApi(String baseUrl, Class<T> cls,Proxy.Type type,String ip,int port) {
        Retrofit retrofit = retrofitMap.get(baseUrl);
        if (retrofit == null) {
            if (!baseUrl.endsWith("/"))
                baseUrl += "/";
            retrofit = new Retrofit.Builder().client(getOkHttpClient(new Proxy(type,
                    new InetSocketAddress(ip,port)))).baseUrl(baseUrl).addConverterFactory(JacksonConverterFactory.create()).build();
            retrofitMap.put(baseUrl, retrofit);
        }
        return retrofit.create(cls);
    }

    public synchronized static <T> T getXmlApi(String baseUrl, Class<T> cls) {
        Retrofit retrofit = retrofitMap.get(baseUrl);
        if (retrofit == null) {
            if (!baseUrl.endsWith("/"))
                baseUrl += "/";
            retrofit = new Retrofit.Builder().client(getOkHttpClient(Proxy.NO_PROXY)).baseUrl(baseUrl).addConverterFactory(SimpleXmlConverterFactory.create()).build();
            retrofitMap.put(baseUrl, retrofit);
        }
        return retrofit.create(cls);
    }

    private static OkHttpClient getOkHttpClient(Proxy proxy) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().cookieJar(new CookieJar() {
            private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url, cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url);
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        }).connectTimeout(30, TimeUnit.SECONDS).
                addInterceptor(logging).
                proxy(globalProxy==null?proxy:globalProxy).build();
    }
    public static void setGlobalProxy(Proxy.Type type,String ip,int port){
        globalProxy = new Proxy(type,
                new InetSocketAddress(ip,port));
    }
}
