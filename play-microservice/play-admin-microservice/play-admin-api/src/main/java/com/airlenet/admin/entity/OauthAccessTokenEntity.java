package com.airlenet.admin.entity;

import com.airlenet.data.jpa.BaseEntity;
import com.airlenet.data.jpa.DataEntity;
import lombok.Data;

import javax.persistence.*;

@Table(name = "oauth_access_token")
@Entity
@Data
public class OauthAccessTokenEntity{

    @Id
    @Column(name = "token_id")
    private String tokenId;

    @Lob
    @Column(name = "token")
    private String token;

    @Column(name = "authentication_id")
    private String authenticationId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "authentication")
    @Lob
    private String authentication;

    @Column(name = "refresh_token")
    private String refreshToken;

}
