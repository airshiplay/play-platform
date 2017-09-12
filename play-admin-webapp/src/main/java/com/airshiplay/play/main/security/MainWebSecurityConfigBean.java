package com.airshiplay.play.main.security;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MainWebSecurityConfigBean {

	@Value("${security.skip_auth_urls?:/assets/**,/extjs/**,/requirejs/**,/,/index,/app_info,/captcha**}")
	private String[] skipAuthUrls;
	// @Value("#{conf['security.filterChain.[<custom name>]']}")
	// private final HashMap<String, String> filterChain = new HashMap<String,
	// String>();
	@Autowired
	WebSecurityManager securityManager;


	@Value("${path.admin}")
	private  String adminPath;
	/**
	 * Shiro的Web过滤器
	 * 
	 * @return
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setSecurityManager(securityManager);
		shiroFilter.setLoginUrl("/signin");
		// shiroFilter.setSuccessUrl("/admin");
		// shiroFilter.setUnauthorizedUrl("login");
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
		filterChainDefinitionMap.put(adminPath.startsWith("/")||adminPath.equals("")?adminPath+"/login":("/"+adminPath+"/login"), "anon");
		filterChainDefinitionMap.put("/requirejs/**", "anon");
        filterChainDefinitionMap.put("captcha**", "anon");
		filterChainDefinitionMap.put("/captcha**", "anon");
		filterChainDefinitionMap.put("/oauth/**", "anon");
		filterChainDefinitionMap.put(adminPath.startsWith("/")?adminPath:"/"+adminPath, "admin");
		filterChainDefinitionMap.put(adminPath.startsWith("/")?adminPath+"/**":"/"+adminPath+"/**", "admin");
		filterChainDefinitionMap.put("/**/**.view", "admin");
		// filterChainDefinitionMap.put("/**", "user");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMap);
		Map<String, Filter> filters = new HashMap<String, Filter>();
		AdminUserFilter adminUserFilter = new AdminUserFilter();
		adminUserFilter.setLoginUrl(adminPath.startsWith("/")||adminPath.equals("")?adminPath+"/login":"/"+adminPath+"/login");
		filters.put("admin", adminUserFilter);
		shiroFilter.setFilters(filters);
		return shiroFilter;
	}

}
