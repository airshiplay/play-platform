package com.airshiplay.play.app.repo;

import com.airlenet.repo.jpa.EntityRepository;
import com.airshiplay.play.app.entity.AppEntity;
public interface AppEntityRepository extends
		EntityRepository<AppEntity, Long> {

}