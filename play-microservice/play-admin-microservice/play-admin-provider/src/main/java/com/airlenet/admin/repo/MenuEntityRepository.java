package com.airlenet.admin.repo;

import com.airlenet.data.jpa.HierarchicalEntityRepository;
import com.airlenet.admin.entity.MenuEntity;

public interface MenuEntityRepository extends HierarchicalEntityRepository<MenuEntity, Long> {

    MenuEntity findByCode(String code);

}
