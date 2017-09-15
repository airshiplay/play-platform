package com.airshiplay.play.plugin.oauth.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.airshiplay.play.plugin.oauth.service.OauthUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.SimpleType;

/**
 * https://open.weixin.qq.com/cgi-bin/showdocument?action=dir_list&t=resource/res_list&verify=1&id=open1419316505&token=098bc4192646af00366bd95987106f6fc9bc5085&lang=zh_CN
 * 
 * @author airshiplay
 *
 */
@Component("wechatOpenOauthPlugin")
public class WeChatOpenOauthPlugin extends OauthPlugin {

	@Value("${info.siteUrl?:http://www.airlenet.com}")
	private String siteUrl;

	@Autowired
	private OauthUserService oauthUserService;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public String getEnterUrl() {
		return "/oauth/authorization/admin/wechatOpenOauthPlugin";
	}

	@Override
	public String getAuthorizationUrl() {
		return "https://open.weixin.qq.com/connect/qrconnect";
	}

	@Override
	public Map<String, Object> getAuthorizationParameterMap(String type) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("appid", getClientId());
		parameterMap.put("redirect_uri", getRedirectUri(type));
		parameterMap.put("response_type", "code");
		parameterMap.put("scope", "snsapi_login");
		parameterMap.put("state", RandomStringUtils.randomAscii(4));
		return parameterMap;
	}
	public String getAuthorizationFrag() {
		return "#wechat_redirect";
	}
	@Override
	public String getAccessToken(String code,String type) {
		Assert.hasText(code);
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("appid", getClientId());
		parameterMap.put("secret", getClientSecret());
		parameterMap.put("code", code);
		parameterMap.put("grant_type", "authorization_code");
		String accessTokenResponse = post("https://api.weixin.qq.com/sns/oauth2/access_token", parameterMap);
		
		Map<String, Object> jsonObject = null;
		try {
			jsonObject = objectMapper.readValue(accessTokenResponse, MapType.construct(HashMap.class, SimpleType.construct(String.class), SimpleType.construct(Object.class)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return  (String) jsonObject.get("access_token")+":"+(String) jsonObject.get("openid");
	}

	@Override
	public OauthUserEntity getOauthUser(String accessToken) {
		Assert.hasText(accessToken);
		Map<String, Object> apiParameterMap = new HashMap<>();
		String str[]=accessToken.split(":");
		apiParameterMap.put("access_token", str[0]);
		apiParameterMap.put("openid", str[1]);
		apiParameterMap.put("lang", "zh_CN");
		String apiResponse = get("https://api.weixin.qq.com/sns/userinfo", apiParameterMap);
		Map<String, Object> jsonObject = null;
		try {
			jsonObject = objectMapper.readValue(apiResponse, MapType.construct(HashMap.class, SimpleType.construct(String.class), SimpleType.construct(Object.class)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String userId = jsonObject.get("openid").toString();
		OauthUserEntity oauthUser = oauthUserService.findByOauthPluginIdAndUserId(getId(), userId);
		if (oauthUser == null) {
			oauthUser = new OauthUserEntity();
			oauthUser.setOauthPluginId(getId());
			oauthUser.setUserId(userId);
			oauthUser.setUsername((String) jsonObject.get("unionid"));
			oauthUser.setName((String) jsonObject.get("nickname"));
			oauthUser.setAvatarUrl((String) jsonObject.get("headimgurl"));
		}
		return oauthUser;
	}

	@Override
	public String getName() {
		return "wechat";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getAuthor() {
		return "airshiplay";
	}

	@Override
	public String getSiteUrl() {
		return siteUrl;
	}

	@Override
	public String getInstallUrl() {
		return "/oauth/wechatopen/install";
	}

	@Override
	public String getUninstallUrl() {
		return "/oauth/wechatopen/uninstall";
	}

	@Override
	public String getSettingUrl() {
		return "/oauth/wechatopen/setting";
	}

}
