package com.airshiplay.play.main.repo;

import com.airlenet.repo.jpa.EntityRepository;
import com.airshiplay.play.main.entity.SettingEntity;

public interface SettingEntityRepository extends EntityRepository<SettingEntity, Long> {

	SettingEntity findTopByOrderByCreatedDateDesc();
	
}
