package com.airlenet.admin.repo;

import com.airlenet.data.jpa.HierarchicalEntityRepository;
import com.airlenet.admin.entity.DictEntity;

import java.util.List;

public interface DictEntityRepository extends
        HierarchicalEntityRepository<DictEntity, Long> {
    List<DictEntity> findByType(String type);

    int countByType(String type);

}
