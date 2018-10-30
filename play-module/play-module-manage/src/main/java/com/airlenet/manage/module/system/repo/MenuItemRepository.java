package com.airlenet.manage.module.system.repo;

import com.airlenet.data.jpa.HierarchicalEntityRepository;
import com.airlenet.manage.module.system.entity.MenuItem;

public interface MenuItemRepository extends HierarchicalEntityRepository<MenuItem, Long> {

}
