package com.airlenet.auth.config;

import com.airlenet.authorization.core.CustomUserDetails;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class PlayTokenEnhancer implements TokenEnhancer {

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        //加密的外面,同时也放到加密里面
        final Map<String, Object> additionalInfo = new HashMap<>();
        if( authentication.getUserAuthentication()!=null){
            CustomUserDetails<?,?> user = (CustomUserDetails<?,?>) authentication.getUserAuthentication().getPrincipal();
            additionalInfo.put("username", user.getUsername());
            additionalInfo.put("user_id", user.getId());
            additionalInfo.put("authorities",AuthorityUtils.authorityListToSet(user.getAuthorities()));
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        }
        return accessToken;
    }

}