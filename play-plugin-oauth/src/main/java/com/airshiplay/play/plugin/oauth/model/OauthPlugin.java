package com.airshiplay.play.plugin.oauth.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.airlenet.plugin.core.Plugin;
import com.airlenet.plugin.core.config.PluginConfigEntity;
import com.google.common.base.Objects;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.util.Assert;

public abstract class OauthPlugin extends Plugin {

	public static final String CLIENT_ID_ATTRIBUTE_NAME = "client_id";

	public static final String CLIENT_SECRET_ATTRIBUTE_NAME = "client_secret";

	/** ICON属性名称 */
	public static final String ICON_ATTRIBUTE_NAME = "icon";

	/** 描述属性名称 */
	public static final String DESCRIPTION_ATTRIBUTE_NAME = "description";

	/**
	 * 获取客户端ID
	 * 
	 * @return
	 */
	public String getClientId() {
		PluginConfigEntity pluginConfig = getPluginConfig();
		return pluginConfig != null ? pluginConfig.getAttribute(CLIENT_ID_ATTRIBUTE_NAME) : null;
	}

	/**
	 * 获取客户端密钥
	 * 
	 * @return
	 */
	public String getClientSecret() {
		PluginConfigEntity pluginConfig = getPluginConfig();
		return pluginConfig != null ? pluginConfig.getAttribute(CLIENT_SECRET_ATTRIBUTE_NAME) : null;
	}

	/**
	 * 获取LOGO
	 * 
	 * @return LOGO
	 */
	public String getLogo() {
		PluginConfigEntity pluginConfig = getPluginConfig();
		return pluginConfig != null ? pluginConfig.getAttribute(ICON_ATTRIBUTE_NAME) : null;
	}

	public abstract String getEnterUrl();
	/**
	 * 获取描述
	 * 
	 * @return 描述
	 */
	public String getDescription() {
		PluginConfigEntity pluginConfig = getPluginConfig();
		return pluginConfig != null ? pluginConfig.getAttribute(DESCRIPTION_ATTRIBUTE_NAME) : null;
	}

	public String getRedirectUri(String type) {
		return getSiteUrl() + "/oauth/api/"+type+"/" + getId();
	}

	public abstract String getAuthorizationUrl();

	public abstract Map<String, Object> getAuthorizationParameterMap(String source);

	public abstract String getAccessToken(String code, String source);

	public abstract OauthUserEntity getOauthUser(String accessToken);

	public String getAuthorizationFrag() {
		return "";
	}



	/**
	 * 连接Map键值对
	 *
	 * @param map
	 *            Map
	 * @param prefix
	 *            前缀
	 * @param suffix
	 *            后缀
	 * @param separator
	 *            连接符
	 * @param ignoreEmptyValue
	 *            忽略空值
	 * @param ignoreKeys
	 *            忽略Key
	 * @return 字符串
	 */
	protected String joinKeyValue(Map<String, Object> map, String prefix, String suffix, String separator, boolean ignoreEmptyValue, String... ignoreKeys) {
		List<String> list = new ArrayList<String>();
		if (map != null) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				String value = ConvertUtils.convert(entry.getValue());
				if (StringUtils.isNotEmpty(key) && !ArrayUtils.contains(ignoreKeys, key) && (!ignoreEmptyValue || StringUtils.isNotEmpty(value))) {
					list.add(key + "=" + (value != null ? value : ""));
				}
			}
		}
		return (prefix != null ? prefix : "") + StringUtils.join(list, separator) + (suffix != null ? suffix : "");
	}

	/**
	 * 连接Map值
	 *
	 * @param map
	 *            Map
	 * @param prefix
	 *            前缀
	 * @param suffix
	 *            后缀
	 * @param separator
	 *            连接符
	 * @param ignoreEmptyValue
	 *            忽略空值
	 * @param ignoreKeys
	 *            忽略Key
	 * @return 字符串
	 */
	protected String joinValue(Map<String, Object> map, String prefix, String suffix, String separator, boolean ignoreEmptyValue, String... ignoreKeys) {
		List<String> list = new ArrayList<String>();
		if (map != null) {
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				String key = entry.getKey();
				String value = ConvertUtils.convert(entry.getValue());
				if (StringUtils.isNotEmpty(key) && !ArrayUtils.contains(ignoreKeys, key) && (!ignoreEmptyValue || StringUtils.isNotEmpty(value))) {
					list.add(value != null ? value : "");
				}
			}
		}
		return (prefix != null ? prefix : "") + StringUtils.join(list, separator) + (suffix != null ? suffix : "");
	}

	/**
	 * POST请求
	 *
	 * @param url
	 *            URL
	 * @param parameterMap
	 *            请求参数
	 * @return 返回结果
	 */
	protected String post(String url, Map<String, Object> parameterMap) {
		Assert.hasText(url);
		String result = null;
		HttpClient httpClient = HttpClientBuilder.create().build();
		try {
			HttpPost httpPost = new HttpPost(url);
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			if (parameterMap != null) {
				for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
					String name = entry.getKey();
					String value = ConvertUtils.convert(entry.getValue());
					if (StringUtils.isNotEmpty(name)) {
						nameValuePairs.add(new BasicNameValuePair(name, value));
					}
				}
			}
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			result = EntityUtils.toString(httpEntity);
			EntityUtils.consume(httpEntity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				((CloseableHttpClient) httpClient).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	protected String getParameter(List<NameValuePair> nameValuePairs, String param) {
		for (NameValuePair nameValuePair : nameValuePairs) {
			if (Objects.equal(param, nameValuePair.getName())) {
				return nameValuePair.getValue();
			}
		}
		return null;
	}

	/**
	 * GET请求
	 *
	 * @param url
	 *            URL
	 * @param parameterMap
	 *            请求参数
	 * @return 返回结果
	 */
	protected String get(String url, Map<String, Object> parameterMap) {
		Assert.hasText(url);
		String result = null;
		HttpClient httpClient = HttpClientBuilder.create().build();
		try {
			List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
			if (parameterMap != null) {
				for (Map.Entry<String, Object> entry : parameterMap.entrySet()) {
					String name = entry.getKey();
					String value = ConvertUtils.convert(entry.getValue());
					if (StringUtils.isNotEmpty(name)) {
						nameValuePairs.add(new BasicNameValuePair(name, value));
					}
				}
			}
			HttpGet httpGet = new HttpGet(url + (StringUtils.contains(url, "?") ? "&" : "?") + EntityUtils.toString(new UrlEncodedFormEntity(nameValuePairs, "UTF-8")));
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			result = EntityUtils.toString(httpEntity);
			EntityUtils.consume(httpEntity);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				((CloseableHttpClient) httpClient).close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
