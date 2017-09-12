package com.airshiplay.play.plugin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airshiplay.play.main.service.EntityService;
import com.airshiplay.play.plugin.entity.PluginConfigEntity;
import com.airshiplay.play.plugin.repo.PluginConfigEntityRepository;

@Service
public class PluginConfigEntityService extends EntityService<PluginConfigEntity, Long> {

	@Autowired
	private PluginConfigEntityRepository pluginConfigRepository;

	public boolean pluginIdExists(String pluginId) {
		return pluginConfigRepository.countByPluginId(pluginId) > 0;
	}

	public PluginConfigEntity findByPluginId(String pluginId) {
		return pluginConfigRepository.findFirstByPluginId(pluginId);
	}

	public void deleteByPluginId(String pluginId) {
		pluginConfigRepository.deleteByPluginId(pluginId);
	}

}
