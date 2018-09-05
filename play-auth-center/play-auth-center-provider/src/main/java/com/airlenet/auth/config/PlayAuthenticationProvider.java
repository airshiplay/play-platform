package com.airlenet.auth.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class PlayAuthenticationProvider implements AuthenticationProvider, InitializingBean, MessageSourceAware {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setMessageSource(MessageSource messageSource) {

    }
}
