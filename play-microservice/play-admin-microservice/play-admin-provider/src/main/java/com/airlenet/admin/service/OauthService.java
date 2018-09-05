package com.airlenet.admin.service;

import com.airlenet.admin.entity.OauthClientDetailsEntity;

import java.util.Optional;

public interface OauthService {

    public Optional<OauthClientDetailsEntity> findOauthClientDetailsById(String clientId) ;

    public OauthClientDetailsEntity saveOauthClientDetails(OauthClientDetailsEntity oauthClientDetailsEntity);
}
