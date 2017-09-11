package com.airlenet.security.shiro.authc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.shiro.authz.annotation.Logical;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresPagePermissions {
    /**
     * The permission string which will be passed to {@link org.apache.shiro.subject.Subject#isPermitted(String)}
     * to determine if the user is allowed to invoke the code protected by this annotation.
     */
    String[] value();
    
    /**
     * The logical operation for the permission checks in case multiple roles are specified. AND is the default
     * @since 1.1.0
     */
    Logical logical() default Logical.AND; 
}
