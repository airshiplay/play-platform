package com.airlenet.security;

import java.util.Arrays;

import com.airlenet.security.shiro.realm.UserRealm;
import com.airlenet.security.spring.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.WebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import com.airlenet.security.shiro.authc.PlayHashedCredentialsMatcher;
import com.airlenet.security.shiro.authc.PlayOauthCredentialsMatcher;
import com.airlenet.security.shiro.realm.OauthRealm;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ShiroConfigBean {

    @Value("${shiro.algorithm_name?:"+ Md5Hash.ALGORITHM_NAME+"}")
    private String hashAlgorithmName;

    @Value("${shiro.password_retry?:-1}")
    private int passwordRetry;

	@Bean
	public EhCacheManager getEhCacheManager() {
		EhCacheManager em = new EhCacheManager();
		em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
		return em;
	}

	/**
	 * 安全管理器
	 * 
	 * @return
	 */
	@Bean
	public WebSecurityManager securityManager() {
		DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
		// webSecurityManager.setRealm(userRealm());
		webSecurityManager.setRealms(Arrays.asList(userRealm(), oauthRealm()));
		webSecurityManager.setCacheManager(getEhCacheManager());
		webSecurityManager.setRememberMeManager(rememberMeManager());
		// webSecurityManager.setSessionManager(sessionManager());
		return webSecurityManager;
	}

	@Bean
	public Realm oauthRealm() {
		OauthRealm realm = new OauthRealm();
		realm.setCredentialsMatcher(new PlayOauthCredentialsMatcher());
		return realm;
	}

	@Bean
	public Realm userRealm() {
		UserRealm realm = new UserRealm();
//        PlayHashedCredentialsMatcher playHashedCredentialsMatcher = new PlayHashedCredentialsMatcher(hashAlgorithmName);
        realm.setCredentialsMatcher(credentialsMatcher());
		return realm;
	}

	@Bean(name = "credentialsMatcher")
	public PlayHashedCredentialsMatcher credentialsMatcher() {
	    if(hashAlgorithmName == null){
            hashAlgorithmName = Md5Hash.ALGORITHM_NAME;
        }
		final PlayHashedCredentialsMatcher credentialsMatcher = new PlayHashedCredentialsMatcher(hashAlgorithmName);
//		credentialsMatcher.setPasswordService(passwordService());
        credentialsMatcher.setRetryCount(passwordRetry);
		return credentialsMatcher;
	}

	@Bean
	public PlayPasswordService passwordService() {
		return new PlayPasswordService();
	}

	/**
	 * rememberMe管理器
	 * 
	 * @return
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
		rememberMeManager.setCipherKey(org.apache.shiro.codec.Base64
				.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		rememberMeManager.setCookie(rememberMeCookie());
		return rememberMeManager;
	}

	@Bean
	public Cookie rememberMeCookie() {
		SimpleCookie simpleCookie = new org.apache.shiro.web.servlet.SimpleCookie(
				"rememberMe");
		simpleCookie.setHttpOnly(true);
		simpleCookie.setMaxAge(2592000);// 30天
		simpleCookie.setDomain("");
		simpleCookie.setPath("/");

		return simpleCookie;
	}

	/**
	 * 会话管理器
	 * 
	 * @return
	 */
	@Bean
	public SessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new org.apache.shiro.web.session.mgt.DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(1800000);
		sessionManager.setDeleteInvalidSessions(true);
		SimpleCookie sessionIdCookie = new org.apache.shiro.web.servlet.SimpleCookie(
				"sid");
		sessionIdCookie.setHttpOnly(true);
		sessionIdCookie.setMaxAge(-1);
		sessionIdCookie.setDomain("");
		sessionIdCookie.setPath("/");
		sessionManager.setSessionIdCookie(sessionIdCookie);
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionDAO(null);
		// sessionManager.setSessionValidationScheduler(null);
		// sessionManager.setSessionValidationSchedulerEnabled(true);
		return sessionManager;
	}

	/**
	 * Shiro生命周期处理器
	 * 
	 * @return
	 */
	@Bean(name = "lifecycleBeanPostProcessor")
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	@DependsOn(value = "lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAdvisor() {
		AuthorizationAttributeSourceAdvisor authorizationAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAdvisor.setSecurityManager(securityManager());
		return authorizationAdvisor;
	}

	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean() {
		MethodInvokingFactoryBean methodInvokingFactoryBean = new MethodInvokingFactoryBean();
		methodInvokingFactoryBean
				.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
		methodInvokingFactoryBean
				.setArguments(new Object[] { securityManager() });
		return methodInvokingFactoryBean;
	}

}
