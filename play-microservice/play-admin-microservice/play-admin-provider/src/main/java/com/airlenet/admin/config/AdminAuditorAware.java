package com.airlenet.admin.config;

import com.airlenet.admin.entity.UserEntity;
import com.airlenet.authorization.core.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;
@Component
public class AdminAuditorAware implements AuditorAware<UserEntity> {
    @Override
    public Optional<UserEntity> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Optional.empty();
        }
        Object principal = authentication.getPrincipal();
        @SuppressWarnings("unchecked")
        CustomUserDetails<?, UserEntity> user = (CustomUserDetails<?, UserEntity>) principal;
        return Optional.of(user.getCustomUser());
    }
}
