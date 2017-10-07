package com.airlenet.security.shiro;

import org.apache.shiro.authz.annotation.*;

import javax.ws.rs.Priorities;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author airlenet
 * @version 2017-10-04
 */
public class AuthorizationFilterFeature implements DynamicFeature {

    private static List<Class<? extends Annotation>> shiroAnnotations = Collections.unmodifiableList(Arrays.asList(
            RequiresPermissions.class,
            RequiresRoles.class,
            RequiresAuthentication.class,
            RequiresUser.class,
            RequiresGuest.class, Secured.class));

    @Override
    public void configure(ResourceInfo resourceInfo, FeatureContext context) {

        List<Annotation> authzSpecs = new ArrayList<>();

        for (Class<? extends Annotation> annotationClass : shiroAnnotations) {
            // XXX What is the performance of getAnnotation vs getAnnotations?
            Annotation classAuthzSpec = resourceInfo.getResourceClass().getAnnotation(annotationClass);
            Annotation methodAuthzSpec = resourceInfo.getResourceMethod().getAnnotation(annotationClass);

            if (classAuthzSpec != null) authzSpecs.add(classAuthzSpec);
            if (methodAuthzSpec != null) authzSpecs.add(methodAuthzSpec);
        }

        if (!authzSpecs.isEmpty()) {
            context.register(new PlayAuthorizationFilter(authzSpecs), Priorities.AUTHORIZATION);
        }
    }

}

