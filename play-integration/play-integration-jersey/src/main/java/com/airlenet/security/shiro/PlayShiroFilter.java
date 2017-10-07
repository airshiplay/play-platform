//package com.airlenet.security.shiro;
//
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.AbstractShiroFilter;
//import org.apache.shiro.web.servlet.SimpleCookie;
//
///**
// * @author airlenet
// * @version 2017-10-05
// */
//public class PlayShiroFilter extends AbstractShiroFilter {
//
//    @Override
//    public void init() throws Exception {
//        configure();
//    }
//
//    protected void configure() throws Exception {
//        DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
//        webSecurityManager.setRealm(new FlexAuthorizingReam());
//
//        //认证信息缓存
////        EhCacheManager em = new EhCacheManager();
////        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");
////        webSecurityManager.setCacheManager(em);
//
//        //可以rememberMe
//        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
//        rememberMeManager.setCipherKey(org.apache.shiro.codec.Base64
//                .decode("4AvVhmFLUs0KTA3Kprsdag=="));
//        SimpleCookie simpleCookie = new org.apache.shiro.web.servlet.SimpleCookie(
//                "rememberMe");
//        simpleCookie.setHttpOnly(true);
//        simpleCookie.setMaxAge(2592000);// 30天
//        simpleCookie.setDomain("");
//        simpleCookie.setPath("/");
//        rememberMeManager.setCookie(simpleCookie);
//        webSecurityManager.setRememberMeManager(rememberMeManager);
//        setSecurityManager(webSecurityManager);
//    }
//}
