package com.airlenet.security.spring;

import java.util.Collection;

import com.airlenet.security.shiro.authz.aop.PermissionDataAnnotationMethodInterceptor;
import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.aop.MethodInvocation;
import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;
import org.apache.shiro.spring.aop.SpringAnnotationResolver;

public class AopAllianceAnnotationsAuthorizingMethodInterceptor 
extends org.apache.shiro.spring.security.interceptor.AopAllianceAnnotationsAuthorizingMethodInterceptor {

	public AopAllianceAnnotationsAuthorizingMethodInterceptor(){
		super();
		AnnotationResolver resolver = new SpringAnnotationResolver();
		this.methodInterceptors.add(new PermissionDataAnnotationMethodInterceptor(resolver));
		
	}
	
	
	/**
	 * 必须拦截此方法，增加对DataAnnotationMethodInterceptor的处理
	 * 
	 * 
	 * */
	
	@Override
	public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        org.apache.shiro.aop.MethodInvocation mi = createMethodInvocation(methodInvocation);
        assertAuthorized(mi);
        
        Collection<AuthorizingAnnotationMethodInterceptor> aamis = getMethodInterceptors();
        if (aamis != null && !aamis.isEmpty()) {
            for (AuthorizingAnnotationMethodInterceptor aami : aamis) {
                if (aami.supports(mi)){
                	
                	//针对DataAnnotationMethodInterceptor，有特殊的处理
                	if(aami instanceof PermissionDataAnnotationMethodInterceptor) {
                		return ((PermissionDataAnnotationMethodInterceptor)aami).invoke(mi);
                	}
                	
                } 
            }
        }
        
        //其它情况均使用系统缺省
        return super.invoke(mi);
    }

}