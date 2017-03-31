package com.airshiplay.play.plugin.pay.model;

import java.util.Map;

import com.airshiplay.play.plugin.entity.Plugin;
import com.airshiplay.play.plugin.entity.PluginConfigEntity;

public abstract class PayPlugin extends Plugin {

	public static final String CLIENT_ID_ATTRIBUTE_NAME = "app_id";

	public static final String CLIENT_SECRET_ATTRIBUTE_NAME = "app_secret";

	/** ICON属性名称 */
	public static final String ICON_ATTRIBUTE_NAME = "icon";

	/** 描述属性名称 */
	public static final String DESCRIPTION_ATTRIBUTE_NAME = "description";

	/**
	 * 获取客户端ID
	 * 
	 * @return
	 */
	public String getAppId() {
		PluginConfigEntity pluginConfig = getPluginConfig();
		return pluginConfig != null ? pluginConfig.getAttribute(CLIENT_ID_ATTRIBUTE_NAME) : null;
	}

	/**
	 * 获取客户端密钥
	 * 
	 * @return
	 */
	public String getAppSecret() {
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

 
	public String getAuthorizationFrag() {
		return "";
	}
}
