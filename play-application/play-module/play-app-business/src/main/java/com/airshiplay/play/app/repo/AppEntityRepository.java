package com.airshiplay.play.app.repo;

import com.airshiplay.play.app.entity.AppEntity;
import com.airshiplay.play.repo.jpa.BaseJpaRepository;

public interface AppEntityRepository extends
		BaseJpaRepository<AppEntity, Long> {

}