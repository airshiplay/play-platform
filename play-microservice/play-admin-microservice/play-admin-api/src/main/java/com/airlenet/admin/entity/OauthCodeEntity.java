package com.airlenet.admin.entity;

import com.airlenet.data.jpa.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "oauth_code")
public class OauthCodeEntity {


    @Id
    @Column(name = "code")
    private String code;

    @Lob
    @Column(name = "authentication")
    private String authentication;
}
