package com.airlenet.admin.entity;

import com.airlenet.data.domain.Enabledable;
import com.airlenet.data.jpa.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "oauth_client_details")
@Data
public class OauthClientDetailsEntity extends BaseEntity<Long> implements Enabledable {
    @ApiModelProperty(value = "主键,必须唯一,不能为空. ",
            notes = "用于唯一标识每一个客户端(client); 在注册时必须填写(也可由服务端自动生成). \n" +
            "对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appKey,与client_id是同一个概念.")

    @Nullable
    @Column(name = "client_id")
    private String clientId;

    @ApiModelProperty(notes = "客户端所能访问的资源id集合,多个资源时用逗号(,)分隔,如: \"unity-resource,mobile-resource\". \n" +
            "该字段的值必须来源于与security.xml中标签‹oauth2:resource-server的属性resource-id值一致. 在security.xml配置有几个‹oauth2:resource-server标签, 则该字段可以使用几个该值. \n" +
            "在实际应用中, 我们一般将资源进行分类,并分别配置对应的‹oauth2:resource-server,如订单资源配置一个‹oauth2:resource-server, 用户资源又配置一个‹oauth2:resource-server. 当注册客户端时,根据实际需要可选择资源id,也可根据不同的注册流程,赋予对应的资源id.")
    @Column(name = "resource_ids")
    private String resourceIds;


@ApiModelProperty(notes = "用于指定客户端(client)的访问密匙; 在注册时必须填写(也可由服务端自动生成). \n" +
        "对于不同的grant_type,该字段都是必须的. 在实际应用中的另一个名称叫appSecret,与client_secret是同一个概念.")
    @Column(name = "client_secret")
    @Nullable
    private String clientSecret;

@ApiModelProperty(notes = "指定客户端申请的权限范围,可选值包括read,write,trust;若有多个权限范围用逗号(,)分隔,如: \"read,write\". \n" +
        "scope的值与security.xml中配置的‹intercept-url的access属性有关系. 如‹intercept-url的配置为\n" +
        "‹intercept-url pattern=\"/m/**\" access=\"ROLE_MOBILE,SCOPE_READ\"/>\n" +
        "则说明访问该URL时的客户端必须有read权限范围. write的配置值为SCOPE_WRITE, trust的配置值为SCOPE_TRUST. \n" +
        "在实际应该中, 该值一般由服务端指定, 常用的值为read,write.")
    @Column(name = "scope")
    @Nullable
    private String scope;


@ApiModelProperty(notes = "指定客户端支持的grant_type,可选值包括authorization_code,password,refresh_token,implicit,client_credentials, 若支持多个grant_type用逗号(,)分隔,如: \"authorization_code,password\". \n" +
        "在实际应用中,当注册时,该字段是一般由服务器端指定的,而不是由申请者去选择的,最常用的grant_type组合有: \"authorization_code,refresh_token\"(针对通过浏览器访问的客户端); \"password,refresh_token\"(针对移动设备的客户端). \n" +
        "implicit与client_credentials在实际中很少使用.")
    @Column(name = "authorized_grant_types")
    @Nullable
    private String authorizedGrantTypes;


@ApiModelProperty(value = "客户端的重定向URI,可为空",notes = "客户端的重定向URI,可为空, 当grant_type为authorization_code或implicit时, 在Oauth的流程中会使用并检查与注册时填写的redirect_uri是否一致. 下面分别说明:\n" +
        "当grant_type=authorization_code时, 第一步 从 spring-oauth-server获取 'code'时客户端发起请求时必须有redirect_uri参数, 该参数的值必须与 web_server_redirect_uri的值一致. 第二步 用 'code' 换取 'access_token' 时客户也必须传递相同的redirect_uri. \n" +
        "在实际应用中, web_server_redirect_uri在注册时是必须填写的, 一般用来处理服务器返回的code, 验证state是否合法与通过code去换取access_token值. \n" +
        "在spring-oauth-client项目中, 可具体参考AuthorizationCodeController.java中的authorizationCodeCallback方法.\n" +
        "当grant_type=implicit时通过redirect_uri的hash值来传递access_token值.如:\n" +
        "http://localhost:7777/spring-oauth-client/implicit#access_token=dc891f4a-ac88-4ba6-8224-a2497e013865&token_type=bearer&expires_in=43199\n" +
        "然后客户端通过JS等从hash值中取到access_token值.")
    @Column(name = "web_server_redirect_uri")
    private String webServerRedirectUri;

    @ApiModelProperty(notes = "指定客户端所拥有的Spring Security的权限值,可选, 若有多个权限值,用逗号(,)分隔, 如: \"ROLE_UNITY,ROLE_USER\". \n" +
            "对于是否要设置该字段的值,要根据不同的grant_type来判断, 若客户端在Oauth流程中需要用户的用户名(username)与密码(password)的(authorization_code,password), \n" +
            "则该字段可以不需要设置值,因为服务端将根据用户在服务端所拥有的权限来判断是否有权限访问对应的API. \n" +
            "但如果客户端在Oauth流程中不需要用户信息的(implicit,client_credentials), \n" +
            "则该字段必须要设置对应的权限值, 因为服务端将根据该字段值的权限来判断是否有权限访问对应的API. \n" +
            "(请在spring-oauth-client项目中来测试不同grant_type时authorities的变化)")
    @Column(name = "authorities")
    private String authorities;

    @Column(name = "access_token_validity")
    private Integer accessTokenValidity;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

    @Column(name = "additional_information" ,length = 4096)
    private String additionalInformation;

    @Column(name = "autoapprove")
    private String autoApprove;

    private Boolean enabled=Boolean.TRUE;
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public void setEnabled(boolean enabled) {
        this.enabled =enabled;
    }

    @Override
    public void markDisabled() {
        enabled=false;
    }
}
