package com.airlenet.admin.service.impl;

import com.airlenet.admin.entity.OauthClientDetailsEntity;
import com.airlenet.admin.entity.QOauthClientDetailsEntity;
import com.airlenet.admin.repo.OauthClientDetailsRepository;
import com.airlenet.admin.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OauthServiceImpl implements OauthService {

    @Autowired
    private OauthClientDetailsRepository oauthClientDetailsRepository;

    public Optional<OauthClientDetailsEntity> findOauthClientDetailsById(String clientId) {
        return oauthClientDetailsRepository.findOne(
                QOauthClientDetailsEntity.oauthClientDetailsEntity.clientId.eq(clientId));
    }

    public OauthClientDetailsEntity saveOauthClientDetails(OauthClientDetailsEntity oauthClientDetailsEntity){
       return oauthClientDetailsRepository.save(oauthClientDetailsEntity);
    }
}
