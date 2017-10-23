package com.airlenet.security;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.*;

/**
 * @author airlenet
 * @version 2017-10-05
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ShiroWebSecurityConfigBean {

    @Value("${loginUrl?:/signin}")
    private String loginUrl;

    @Value("${security.skip_auth_urls?:/assets/**,/oauth/**,/requirejs/**,/captcha**,captcha**}")
    private String[] skipAuthUrls;

    @Autowired(required = false)
    protected Map<String, Filter> filters = new HashMap<String, Filter>();

    @Autowired(required = false)
    private List<FilterChainDefinition> chainDefinitionList =new ArrayList<>();

    @Autowired
    private ObjectFactory<WebSecurityManager> securityManager;
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager.getObject());
        shiroFilter.setLoginUrl(loginUrl);
        // shiroFilter.setSuccessUrl("/admin");
        // shiroFilter.setUnauthorizedUrl("login");
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        for(String anon:skipAuthUrls){
            filterChainDefinitionMap.put(anon, "anon");
        }
        for(FilterChainDefinition filterChainDefinition:chainDefinitionList){
            filterChainDefinitionMap.put(filterChainDefinition.getUrl(),filterChainDefinition.getFilterName());
        }
        // filterChainDefinitionMap.put("/**", "user");
        shiroFilter.setFilters(filters);
        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilter;
    }


}
