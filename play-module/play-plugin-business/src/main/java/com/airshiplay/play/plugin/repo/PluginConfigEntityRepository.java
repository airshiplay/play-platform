package com.airshiplay.play.plugin.repo;

import com.airshiplay.play.plugin.entity.PluginConfigEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface PluginConfigEntityRepository extends BaseJpaRepository<PluginConfigEntity, Long> {

	Long countByPluginId(String pluginId);

	PluginConfigEntity findFirstByPluginId(String pluginId);

	void deleteByPluginId(String pluginId);

}
