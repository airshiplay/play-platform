package com.airshiplay.play.security.shiro.authz.aop;

import org.apache.shiro.aop.AnnotationResolver;
import org.apache.shiro.authz.aop.AuthorizingAnnotationMethodInterceptor;

public class PermissionDataAnnotationMethodInterceptor extends
		AuthorizingAnnotationMethodInterceptor {

	/**
	 * Default no-argument constructor that ensures this interceptor looks for
	 * {@link org.apache.shiro.authz.annotation.RequiresGuest RequiresGuest}
	 * annotations in a method declaration.
	 */
	public PermissionDataAnnotationMethodInterceptor() {
		super(new PermissionDataAnnotationHandler());
	}

	/**
	 * @param resolver
	 * @since 1.1
	 */
	public PermissionDataAnnotationMethodInterceptor(AnnotationResolver resolver) {
		super(new PermissionDataAnnotationHandler(), resolver);
	}

}
