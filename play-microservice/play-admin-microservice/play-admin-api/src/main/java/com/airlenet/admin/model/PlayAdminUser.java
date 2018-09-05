package com.airlenet.admin.model;

import com.airlenet.admin.entity.UserEntity;
import com.airlenet.authorization.core.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayAdminUser extends CustomUserDetails<Long,UserEntity> {
    UserEntity userEntity;
    public PlayAdminUser(UserEntity userEntity){
        super(userEntity.getId(), userEntity.getUsername(), userEntity.getPassword(), userEntity.getRoles().stream().flatMap(roleEntity -> Stream.of(new SimpleGrantedAuthority(roleEntity.getCode()))).collect(Collectors.toList()));
    }

    @Override
    public UserEntity getCustomUser() {
        return userEntity;
    }
}
