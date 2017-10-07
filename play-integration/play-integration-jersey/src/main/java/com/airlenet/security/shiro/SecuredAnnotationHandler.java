package com.airlenet.security.shiro;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.aop.AuthorizingAnnotationHandler;

import java.lang.annotation.Annotation;

/**
 * @author airlenet
 * @version 2017-10-04
 */
public class SecuredAnnotationHandler  extends AuthorizingAnnotationHandler {

    public SecuredAnnotationHandler() {
        super(Secured.class);
    }

    @Override
    public void assertAuthorized(Annotation a) throws AuthorizationException {
        if (a instanceof Secured && getSubject().getPrincipal() == null) {
            throw new UnauthenticatedException("Attempting to perform a user-only operation.  The current Subject is " +
                    "not a user (they haven't been authenticated or remembered from a previous login).  " +
                    "Access denied.");
        }
    }
}
