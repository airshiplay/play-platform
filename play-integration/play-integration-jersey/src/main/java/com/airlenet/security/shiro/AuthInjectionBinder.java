//package com.airlenet.security.shiro;
//
///**
// * Created by lig on 17/1/17.
// */
//
//import org.glassfish.hk2.api.InjectionResolver;
//import org.glassfish.hk2.api.TypeLiteral;
//import org.glassfish.hk2.utilities.binding.AbstractBinder;
//
//import javax.inject.Singleton;
//
///**
// * Enable injection with the {@link Auth} annotation.
// */
//public class AuthInjectionBinder extends AbstractBinder {
//
//    @Override
//    protected void configure() {
//        bind(AuthParamInjectionResolver.class).in(Singleton.class)
//                .to(new TypeLiteral<InjectionResolver<Auth>>() {
//                });
//    }
//}