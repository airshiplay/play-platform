package com.airlenet.scheduler.module.repo;

import com.airlenet.data.jpa.EntityRepository;
import com.airlenet.scheduler.module.entity.TriggerEntity;
import com.airlenet.scheduler.module.entity.TriggerPrimaryKey;

public interface TriggerRepository extends EntityRepository<TriggerEntity, TriggerPrimaryKey> {

}
