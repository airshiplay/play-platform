package com.airlenet.admin.service.impl;

import com.airlenet.admin.entity.MenuEntity;
import com.airlenet.admin.service.MenuService;
import com.airlenet.data.jpa.EntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl extends EntityServiceImpl<MenuEntity, Long> implements MenuService {
}
