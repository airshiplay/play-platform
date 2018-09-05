package com.airlenet.admin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.header.HeaderWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer
//@Order(3)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter implements Ordered {
    @Autowired
    TokenStore tokenStore;

    @Autowired
    JwtAccessTokenConverter tokenConverter;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId("foo").tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .authorizeRequests()
//                .anyRequest().authenticated();

//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/**").authenticated()
//                .antMatchers(HttpMethod.GET, "/foo").hasAuthority("FOO_READ");

                        http
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.NEVER)
                        .and()
                        .requestMatchers().antMatchers("/user/**")
                        .and()
                        .authorizeRequests()
                        .antMatchers(HttpMethod.GET, "/user/**").access("#oauth2.hasScope('read')")
                        .antMatchers(HttpMethod.POST, "/user/**").access("#oauth2.hasScope('write')")
                        .antMatchers(HttpMethod.PATCH, "/user/**").access("#oauth2.hasScope('write')")
                        .antMatchers(HttpMethod.PUT, "/user/**").access("#oauth2.hasScope('write')")
                        .antMatchers(HttpMethod.DELETE, "/user/**").access("#oauth2.hasScope('write')")
                        .and()
                        .headers().addHeaderWriter(new HeaderWriter() {
                    @Override
                    public void writeHeaders(HttpServletRequest request, HttpServletResponse response) {
                        response.addHeader("Access-Control-Allow-Origin", "*");
                        if (request.getMethod().equals("OPTIONS")) {
                            response.setHeader("Access-Control-Allow-Methods", request.getHeader("Access-Control-Request-Method"));
                            response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
                        }
                    }
                });
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
