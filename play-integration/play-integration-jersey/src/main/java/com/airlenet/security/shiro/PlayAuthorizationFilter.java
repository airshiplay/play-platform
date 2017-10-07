package com.airlenet.security.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.*;
import org.apache.shiro.authz.aop.*;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author airlenet
 * @version 2017-10-04
 */
public class PlayAuthorizationFilter implements ContainerRequestFilter {

    private final Map<AuthorizingAnnotationHandler, Annotation> authzChecks;

    private String defaultSecret="flexOS-L89^&$fH";
    public PlayAuthorizationFilter(Collection<Annotation> authzSpecs) {
        Map<AuthorizingAnnotationHandler, Annotation> authChecks = new HashMap<>(authzSpecs.size());
        for (Annotation authSpec : authzSpecs) {
            authChecks.put(createHandler(authSpec), authSpec);
        }
        this.authzChecks = Collections.unmodifiableMap(authChecks);
    }

    private static AuthorizingAnnotationHandler createHandler(Annotation annotation) {
        Class<?> t = annotation.annotationType();
        if (RequiresPermissions.class.equals(t)) return new PermissionAnnotationHandler();
        else if (RequiresRoles.class.equals(t)) return new RoleAnnotationHandler();
        else if (RequiresUser.class.equals(t)) return new UserAnnotationHandler();
        else if (Secured.class.equals(t)) return new SecuredAnnotationHandler();
        else if (RequiresGuest.class.equals(t)) return new GuestAnnotationHandler();
        else if (RequiresAuthentication.class.equals(t)) return new AuthenticatedAnnotationHandler();
        else throw new IllegalArgumentException("Cannot create a handler for the unknown for annotation " + t);
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try {
//            String authorization = requestContext.getHeaderString("Authorization");
//            FlexAccessToken flexAccessToken = FlexAccessToken.getFlexAccessToken(authorization,defaultSecret);
//            FlexUserDetails<Integer, CfgUser> flexUserDetails = (FlexUserDetails<Integer, CfgUser>) SecurityUtils.getSubject().getPrincipal();
//
//            if (flexUserDetails != null) {
//                //Token认证方式，校验服务器Token是否过期；用户提供的Token和服务器Token一致或用户未提供Token
//                if (flexUserDetails.isTokenType() && flexUserDetails.getFlexAccessToken().tokenExpired() &&
//                        (flexAccessToken == null || flexAccessToken.getAccessToken().equals(flexUserDetails.getFlexAccessToken().getAccessToken()))) {
//                    throw new FlexAuthenticationException("token expired");
//                }
//            }
//            if (flexAccessToken != null) {
//                FlexUsernameAccessToken token = new FlexUsernameAccessToken(flexAccessToken.getUid(), flexAccessToken.getAccessToken());
//                SecurityUtils.getSubject().login(token);
//            }
            for (Map.Entry<AuthorizingAnnotationHandler, Annotation> authzCheck : authzChecks.entrySet()) {
                AuthorizingAnnotationHandler handler = authzCheck.getKey();
                Annotation authzSpec = authzCheck.getValue();
                handler.assertAuthorized(authzSpec);
            }

        } catch (AuthorizationException e) {
            throw new PlayAuthenticationException(e);
        }catch (Exception e){//异常返回401，未授权访问
            throw new PlayAuthenticationException(e);
        }
    }

}