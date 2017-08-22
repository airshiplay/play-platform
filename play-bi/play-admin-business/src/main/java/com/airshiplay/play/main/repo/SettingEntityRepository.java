package com.airshiplay.play.main.repo;

import com.airlenet.play.repo.jpa.BaseJpaRepository;
import com.airshiplay.play.main.entity.SettingEntity;

public interface SettingEntityRepository extends BaseJpaRepository<SettingEntity, Long> {

	SettingEntity findTopByOrderByCreatedDateDesc();
	
}
