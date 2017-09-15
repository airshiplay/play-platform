package com.airshiplay.play.plugin.oauth.model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.airshiplay.play.plugin.oauth.service.OauthUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.SimpleType;

/**
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842&token=&lang=zh_CN
 * 
 * @author airshiplay
 *
 */
@Component("wechatMpOauthPlugin")
public class WeChatMpOauthPlugin extends OauthPlugin {

	@Value("${info.siteUrl?:http://www.airlenet.com}")
	private String siteUrl;

	@Autowired
	private OauthUserService oauthUserService;

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public String getEnterUrl() {
		return "/oauth/authorization/admin/wechatMpOauthPlugin";
	}

	@Override
	public String getAuthorizationUrl() {
		return "https://open.weixin.qq.com/connect/oauth2/authorize";
	}

	@Override
	public Map<String, Object> getAuthorizationParameterMap(String type) {
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("appid", getClientId());
		parameterMap.put("redirect_uri", getRedirectUri(type));
		parameterMap.put("response_type", "code");
		parameterMap.put("scope", "snsapi_userinfo");
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
		return "WeChat-Mp";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getAuthor() {
		return "ken";
	}


	@Override
	public String getSiteUrl() {
		return siteUrl;
	}

	@Override
	public String getInstallUrl() {
		return "/oauth/wechatmp/install";
	}

	@Override
	public String getUninstallUrl() {
		return "/oauth/wechatmp/uninstall";
	}

	@Override
	public String getSettingUrl() {
		return "/oauth/wechatmp/setting";
	}

}
