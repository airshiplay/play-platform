package com.airlenet.admin.entity;

import com.airlenet.data.jpa.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "oauth_approvals")
@Entity
@IdClass(OauthApprovalsEntity.OauthApprovalsPK.class)
public class OauthApprovalsEntity  {

    @Id
    @Column(name = "userId")
    private String userId ;

    @Id
    @Column(name = "clientId")
    private String clientId ;

    @Id
    @Column(name = "scope")
    private String  scope ;

    @Column(name = "status")
    private String status ;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expiresAt")
    private Date expiresAt ;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastModifiedAt")
    private Date  lastModifiedAt ;


    @Data
    public static class OauthApprovalsPK implements Serializable {
        @Column(name = "userId")
        private String userId ;

        @Column(name = "clientId")
        private String clientId ;

        @Column(name = "scope")
        private String  scope ;

    }
}
