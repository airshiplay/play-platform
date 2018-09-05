package com.airlenet.admin.service.impl;

import com.airlenet.admin.entity.UserEntity;
import com.airlenet.admin.service.AdminEntityService;
import com.airlenet.data.jpa.EntityServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class AdminEntityServiceImpl extends EntityServiceImpl<UserEntity,Long> implements AdminEntityService{
}
