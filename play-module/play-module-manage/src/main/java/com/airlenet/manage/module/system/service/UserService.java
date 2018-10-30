package com.airlenet.manage.module.system.service;

import com.airlenet.manage.module.system.entity.User;
import org.springframework.stereotype.Service;

import com.airlenet.data.jpa.EntityService;

@Service
public class UserService extends EntityService<User, Long> {

}
