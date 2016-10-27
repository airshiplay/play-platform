package com.airshiplay.plugin.oauth.api;

import com.airshiplay.main.domain.User;
import com.airshiplay.plugin.oauth.domain.OauthPlugin;

public interface OauthService {

	User auth(String oauthPluginId, String code);

	OauthPlugin getOauthPlugin(String oauthPluginId);
}
