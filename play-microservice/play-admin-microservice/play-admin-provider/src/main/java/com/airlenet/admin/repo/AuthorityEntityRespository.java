package com.airlenet.admin.repo;

import com.airlenet.data.jpa.EntityRepository;
import com.airlenet.admin.entity.PermissionEntity;

public interface AuthorityEntityRespository extends
        EntityRepository<PermissionEntity, Long> {

}
