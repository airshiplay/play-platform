package com.airlenet.plugin.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlenet.repo.jpa.EntityService;

@Service
public class PluginConfigService extends EntityService<PluginConfigEntity, Long> {

	@Autowired
	private PluginConfigRepository pluginConfigRepository;

	public boolean pluginIdExists(String pluginId) {
		return getRepository().count(QPluginConfigEntity.pluginConfigEntity.pluginId.eq(pluginId)) > 0;
	}

	public PluginConfigEntity findByPluginId(String pluginId) {
		return pluginConfigRepository.findByPluginId(pluginId);
	}
}
