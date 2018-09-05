package com.airlenet.auth.config;

import com.airlenet.auth.service.PlayUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(2)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PlayUserDetailsService playUserDetailsService;


    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/favicon.ico","/webjars/**","/js/**","/css/**","/img/**","/captcha.jpg");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .headers().frameOptions().sameOrigin()
                // 配置登陆页/login并允许访问
                .and().formLogin()
                .loginPage("/login")
                .permitAll()
                // 登出页
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                // 其余所有请求全部需要鉴权认证
                .and().authorizeRequests()
                .antMatchers("/auth","/webjars/**","/js/**","/css/**","/img/**","/captcha.jpg","/error").permitAll()
                .anyRequest().authenticated()
                // 由于使用的是JWT，我们这里不需要csrf
                .and().csrf().ignoringAntMatchers("/h2-console/*")
//                .disable()
        ;
//        http.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
        auth.userDetailsService(playUserDetailsService);
    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setUserDetailsService(playUserDetailsService);
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        return daoAuthenticationProvider;
//    }
}