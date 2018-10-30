package com.airlenet.manage.module.system.service;

import com.airlenet.manage.module.system.entity.MenuItem;
import org.springframework.stereotype.Service;

import com.airlenet.data.jpa.HierarchicalEntityService;

@Service
public class MenuItemService extends HierarchicalEntityService<MenuItem, Long> {

}
