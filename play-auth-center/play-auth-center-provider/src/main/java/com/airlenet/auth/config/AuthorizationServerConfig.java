package com.airlenet.auth.config;

import com.airlenet.auth.service.PlayUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;
import org.springframework.security.oauth2.provider.request.DefaultOAuth2RequestFactory;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private PlayUserDetailsService playUserDetailsService;
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;


    @Bean
    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public ApprovalStore approvalStore() {
        return new JdbcApprovalStore(dataSource);
    }

    @Bean()
    public JdbcClientDetailsService clientDetailsService() {
        return new JdbcClientDetailsService(dataSource);
    }

    @Bean
    public AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Bean
    public OAuth2RequestFactory oAuth2RequestFactory() {
        return new DefaultOAuth2RequestFactory(clientDetailsService());
    }

    @Bean
    public UserApprovalHandler userApprovalHandler() {
//        TokenStoreUserApprovalHandler userApprovalHandler = new TokenStoreUserApprovalHandler();
////        userApprovalHandler.setOauthService(oauthService);
//        userApprovalHandler.setTokenStore(tokenStore());
//        userApprovalHandler.setClientDetailsService(clientDetailsService());
//        userApprovalHandler.setRequestFactory(oAuth2RequestFactory());
//        return userApprovalHandler;
        return new PlayUserApprovalHandler(clientDetailsService(),approvalStore(),oAuth2RequestFactory());
    }
    @Bean
    public OAuth2AuthenticationEntryPoint oauth2AuthenticationEntryPoint() {
        return new OAuth2AuthenticationEntryPoint();
    }

    @Bean
    public OAuth2AccessDeniedHandler oauth2AccessDeniedHandler() {
        return new OAuth2AccessDeniedHandler();
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new PlayTokenEnhancer();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
        accessTokenConverter.setSigningKey("123");
//        KeyPair keyPair = new KeyStoreKeyFactory(
//                new ClassPathResource("keystore.jks"), "foobar".toCharArray())
//                .getKeyPair("test");
//        accessTokenConverter.setKeyPair(keyPair);
        accessTokenConverter.setAccessTokenConverter(new PlayAccessTokenConverter());
        return accessTokenConverter;
    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore())
//                .accessTokenConverter(accessTokenConverter())
                .authorizationCodeServices(authorizationCodeServices())
                .userDetailsService(playUserDetailsService)
                .userApprovalHandler(userApprovalHandler())
                .authenticationManager(authenticationManager);

        // 自定义token生成方式
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
        endpoints.tokenEnhancer(tokenEnhancerChain);

        // 配置TokenServices参数
        DefaultTokenServices tokenServices = (DefaultTokenServices) endpoints.getDefaultAuthorizationServerTokenServices();
        tokenServices.setTokenStore(endpoints.getTokenStore());
        tokenServices.setSupportRefreshToken(false);
        tokenServices.setClientDetailsService(endpoints.getClientDetailsService());
        tokenServices.setTokenEnhancer(endpoints.getTokenEnhancer());
        tokenServices.setAccessTokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1)); // 1天
        endpoints.tokenServices(tokenServices);
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
       //使用JdbcClientDetailsService客户端详情服务
        clients.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.realm("Play Auth Client")
                .allowFormAuthenticationForClients()
                .accessDeniedHandler(oauth2AccessDeniedHandler())
                .authenticationEntryPoint(oauth2AuthenticationEntryPoint())
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");
    }
}
