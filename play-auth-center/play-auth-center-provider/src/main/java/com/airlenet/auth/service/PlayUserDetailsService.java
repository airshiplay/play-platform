package com.airlenet.auth.service;

import com.airlenet.admin.client.UserClient;
import com.airlenet.admin.entity.UserEntity;
import com.airlenet.admin.model.PlayAdminUser;
import com.airlenet.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlayUserDetailsService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired(required = false)
    UserClient userClient;

    @Autowired(required = false)
    UserService userService;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> adminUserEntityOptional =null;
        if(userClient ==null){
            adminUserEntityOptional = userService.findByUsername(username);
        }else{
           adminUserEntityOptional = userClient.findByUsername(username);
        }
        if(adminUserEntityOptional.isPresent()){
          return new PlayAdminUser(adminUserEntityOptional.get());
        }else{
            throw new UsernameNotFoundException(username);
        }
    }
}
