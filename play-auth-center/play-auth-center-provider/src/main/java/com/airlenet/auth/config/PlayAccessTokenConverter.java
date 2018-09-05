package com.airlenet.auth.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

public class PlayAccessTokenConverter extends DefaultAccessTokenConverter {


    public PlayAccessTokenConverter() {
        super.setUserTokenConverter(new CustomerUserAuthenticationConverter());
    }



    private class CustomerUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

        @Override
        public Map<String, ?> convertUserAuthentication(Authentication authentication) {
            //加密的里面
            LinkedHashMap response = new LinkedHashMap();
            response.put("username", authentication.getName());
            response.put("name", ((User) authentication.getPrincipal()).getUsername());
//            response.put("id", ((User) authentication.getPrincipal()).getId());
//            response.put("createAt", ((User) authentication.getPrincipal()).getCreateAt());
            if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
                response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
            }

            return response;
        }
    }

}