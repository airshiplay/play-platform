package com.airlenet.authorization.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class Oauth2WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http    // 配置登陆页/login并允许访问
                .formLogin().permitAll()
                // 登出页
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/")
                // 其余所有请求全部需要鉴权认证
                .and().authorizeRequests().anyRequest().authenticated()
                // 由于使用的是JWT，我们这里不需要csrf
                .and().csrf().disable();
    }

    /**
     * 用户验证
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception  {
        auth.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("admin").password("123456").authorities("ADMIN");
//        auth.authenticationProvider(daoAuthenticationProvider());
    }


//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        // 设置userDetailsService
//        provider.setUserDetailsService(baseUserDetailService);
//        // 禁止隐藏用户未找到异常
//        provider.setHideUserNotFoundExceptions(false);
//        // 使用BCrypt进行密码的hash
//        provider.setPasswordEncoder(new BCryptPasswordEncoder(6));
//        return provider;
//    }
}