package com.airlenet.admin.entity;

import com.airlenet.data.jpa.BaseEntity;
import com.airlenet.data.jpa.DataEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

@Table(name = "oauth_client_token")
@Entity
@Data
public class OauthClientTokenEntity  {


    @Id
    @ApiModelProperty("从服务器端获取到的access_token的值.")
    @Column(name = "token_id")
    private String tokenId;

    @ApiModelProperty(notes = "这是一个二进制的字段, 存储的数据是OAuth2AccessToken.java对象序列化后的二进制数据.")
    @Lob
    private String token;

    @ApiModelProperty("该字段具有唯一性, 是根据当前的username(如果有),client_id与scope通过MD5加密生成的. \n" +
            "具体实现请参考DefaultClientKeyGenerator.java类.")
    @Column(name = "authentication_id")
    private String authenticationId;

    @Column(name = "user_name")
    private String username;

    @Column(name = "client_id")
    private String clientId;
}
