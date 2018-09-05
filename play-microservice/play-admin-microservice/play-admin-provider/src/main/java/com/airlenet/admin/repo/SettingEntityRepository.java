package com.airlenet.admin.repo;

import com.airlenet.admin.entity.SettingEntity;
import com.airlenet.data.jpa.EntityRepository;

public interface SettingEntityRepository extends EntityRepository<SettingEntity, Long> {

    SettingEntity findTopByOrderByCreatedDateDesc();

}
