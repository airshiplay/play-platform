package com.airlenet.authorization.core;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    public CurrentUserMethodArgumentResolver() {
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.hasParameterAnnotation(CurrentUser.class)) {
            if(CustomUserDetails.class.isAssignableFrom(parameter.getParameterType())){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Object user= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(CustomUserDetails.class.isAssignableFrom(user.getClass())){
            return user;
        }else{
//            new MissingSessionUserException(new AdviceMessage<String>("",null));
            return null;
        }
    }
}