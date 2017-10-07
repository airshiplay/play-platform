package com.airlenet.integration.jersey;

import com.airlenet.security.shiro.AuthorizationFilterFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.validation.internal.ValidationExceptionMapper;

/**
 * @author airlenet
 * @version 2017-10-01
 */
public class JerseyWebApplication extends ResourceConfig {

    public JerseyWebApplication() {
        register(MultiPartFeature.class);
        register(JacksonFeature.class);
        register(PlayObjectMapperProvider.class);
        //validation
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        property(ServerProperties.BV_DISABLE_VALIDATE_ON_EXECUTABLE_OVERRIDE_CHECK, true);
        register(ValidationExceptionMapper.class);
        //exception
        register(ExceptionMapperSupport.class);
        // Authorization
        register(new AuthorizationFilterFeature());
//        register(new SubjectFactory());
//        register(new AuthInjectionBinder());
        // i18n
        register(I18NFilter.class);
    }
}
