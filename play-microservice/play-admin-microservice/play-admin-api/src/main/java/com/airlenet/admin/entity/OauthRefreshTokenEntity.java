package com.airlenet.admin.entity;

import com.airlenet.data.jpa.BaseEntity;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "oauth_refresh_token")
public class OauthRefreshTokenEntity {

    @Id
    @Column(name = "token_id")
    private String tokenId ;
    @Lob
    @Column(name = "token")
    private String  token ;
    @Lob
    @Column(name = "authentication")
    private String  authentication ;
}
