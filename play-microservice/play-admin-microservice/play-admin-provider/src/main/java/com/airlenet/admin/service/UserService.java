package com.airlenet.admin.service;

import com.airlenet.admin.entity.UserEntity;
import com.airlenet.data.jpa.EntityService;

import java.util.Optional;

public interface UserService extends EntityService<UserEntity, Long> {

    public Optional<UserEntity> findByUsername(String username);

    public void saveUserAndRole(UserEntity userEntity) ;
}
