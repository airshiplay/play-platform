package com.airlenet.admin.repo;

import com.airlenet.admin.entity.UserEntity;
import com.airlenet.data.jpa.EntityRepository;

public interface AdminEntityRepository extends EntityRepository<UserEntity,String> {
}
