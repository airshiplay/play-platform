package com.airlenet.plugin.core.config;

import com.airlenet.repo.jpa.EntityRepository;

public interface PluginConfigRepository extends EntityRepository<PluginConfigEntity, Long> {

	PluginConfigEntity findByPluginId(String pluginId);

}
