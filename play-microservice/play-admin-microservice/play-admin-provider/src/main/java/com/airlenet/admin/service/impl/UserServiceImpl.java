package com.airlenet.admin.service.impl;

import com.airlenet.admin.entity.QUserEntity;
import com.airlenet.admin.entity.RoleEntity;
import com.airlenet.admin.entity.UserEntity;
import com.airlenet.admin.repo.RoleEntityRepository;
import com.airlenet.admin.service.UserRemoteService;
import com.airlenet.admin.service.UserService;
import com.airlenet.data.jpa.EntityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl extends EntityServiceImpl<UserEntity, Long> implements UserService {
    @Autowired
    private RoleEntityRepository roleEntityRepository;
    public Optional<UserEntity> findByUsername(String username) {
        return getRepository().findOne(QUserEntity.userEntity.username.eq(username));
    }

    public void saveUserAndRole(UserEntity userEntity) {
        Set<RoleEntity> roles = userEntity.getRoles();
        roleEntityRepository.saveAll(roles);
        save(userEntity);
    }
}
