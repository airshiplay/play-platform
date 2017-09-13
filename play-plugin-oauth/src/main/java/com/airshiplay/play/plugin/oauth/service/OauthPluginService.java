package com.airshiplay.play.plugin.oauth.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.plugin.oauth.model.OauthPlugin;

@Service
public class OauthPluginService {

	@Autowired
	private Map<String, OauthPlugin> oauthPluginMap = new HashMap<>();

	public Iterable<OauthPlugin> getOauthPlugins() {
		return oauthPluginMap.values();
	}

	public OauthPlugin getOauthPlugin(String oauthPluginId) {
		return oauthPluginMap.get(oauthPluginId);
	}

	public List<OauthPlugin> getAvailableOauthPlugins() {
		List<OauthPlugin> result = new ArrayList<OauthPlugin>();
		for (Iterator<OauthPlugin> iterator = oauthPluginMap.values().iterator(); iterator.hasNext();) {
			OauthPlugin plugin = (OauthPlugin) iterator.next();
			if (!plugin.isDisabled() && plugin.getIsInstalled()) {
				result.add(plugin);
			}
		}
		return result;
	}

}
