package com.airshiplay.play.main.repo;

import com.airshiplay.play.main.entity.SettingEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface SettingEntityRepository extends BaseJpaRepository<SettingEntity, Long> {

	SettingEntity findTopByOrderByCreatedDateDesc();
	
}
