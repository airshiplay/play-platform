package com.airlenet.security;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

/**
 * @author airlenet
 * @version 2017-10-05
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ShiroWebSecurityConfigBean {

    @Autowired
    private ObjectFactory<WebSecurityManager> securityManager;
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter() {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager.getObject());
//        shiroFilter.setLoginUrl("/signin");
//        // shiroFilter.setSuccessUrl("/admin");
//        // shiroFilter.setUnauthorizedUrl("login");
//        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
//        filterChainDefinitionMap.put(adminPath.startsWith("/")||adminPath.equals("")?adminPath+"/login":("/"+adminPath+"/login"), "anon");
//        for(String anon:skipAuthUrls){
//            filterChainDefinitionMap.put(anon, "anon");
//        }
//        filterChainDefinitionMap.put("/requirejs/**", "anon");
//        filterChainDefinitionMap.put("captcha**", "anon");
//        filterChainDefinitionMap.put("/captcha**", "anon");
//        filterChainDefinitionMap.put("/assets/**", "anon");
//        filterChainDefinitionMap.put("/oauth/**", "anon");
//        filterChainDefinitionMap.put(adminPath.startsWith("/")?adminPath:"/"+adminPath, "admin");
//        filterChainDefinitionMap.put(adminPath.startsWith("/")?adminPath+"/**":"/"+adminPath+"/**", "admin");
//        filterChainDefinitionMap.put("/**/**.view", "admin");
//        // filterChainDefinitionMap.put("/**", "user");
//        shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        Map<String, Filter> filters = new HashMap<String, Filter>();
//        AdminUserFilter adminUserFilter = new AdminUserFilter();
//        adminUserFilter.setLoginUrl(adminPath.startsWith("/")||adminPath.equals("")?adminPath+"/login":"/"+adminPath+"/login");
//        filters.put("admin", adminUserFilter);
//        shiroFilter.setFilters(filters);
        return shiroFilter;
    }


}
