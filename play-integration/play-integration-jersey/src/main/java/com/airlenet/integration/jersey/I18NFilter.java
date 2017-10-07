package com.airlenet.integration.jersey;

import com.airlenet.repo.domain.Result;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.util.StringUtils;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * @author airlenet
 * @version 2017-10-05
 */
@Provider
public class I18NFilter implements ContainerResponseFilter, ContainerRequestFilter {

    @Context
    ResourceBundleMessageSource messageSource;

    /**
     * 对Response返回值进行重写，I18N国际化
     */
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        Object entity = containerResponseContext.getEntity();
        if (entity instanceof Result) {
            List<Locale> list = containerRequestContext.getAcceptableLanguages();
            Result result = ((Result) entity);
            if (result.getCode() != null) {
                Locale locale;
                if (list.isEmpty()) {
                    locale = Locale.getDefault();
                } else {
                    locale = list.get(0);
                }
                String defaultMessage = result.getMessage();
                if (StringUtils.isEmpty(defaultMessage)) {
                    defaultMessage = result.getMessage();
                }
                //lng.sys.error
                String msg=messageSource.getMessage(result.getCode().getLang(), result.getCode().getArgs(), defaultMessage, locale);
                if(StringUtils.isEmpty(msg)){
                    msg=messageSource.getMessage("lng.sys.error", null, "sys.error", locale);
                }
                result.message(msg);
            }
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

    }
}
