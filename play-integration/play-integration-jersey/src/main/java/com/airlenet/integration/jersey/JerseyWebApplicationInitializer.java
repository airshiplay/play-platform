package com.airlenet.integration.jersey;

import com.airlenet.config.StaticConfigSupplier;
import org.apache.commons.configuration2.Configuration;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.filter.DelegatingFilterProxy;

import javax.servlet.*;
import java.util.EnumSet;

/**
 * @author airlenet
 * @version 2017-10-01
 */
@Order(-1)
public class JerseyWebApplicationInitializer implements WebApplicationInitializer {

    private final String SERVLET_NAME="JerseyServlet";

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        /**
         * @link org.glassfish.jersey.server.spring.SpringWebApplicationInitializer
         */
        servletContext.setInitParameter("contextConfigLocation","");
        final Configuration config = StaticConfigSupplier.getConfiguration();
        String resourceConfig = config.getString("integration.root_jersey_resource_config_bean_class");
        if(resourceConfig==null){
            resourceConfig ="com.airlenet.integration.jersey.JerseyWebApplication";
        }
        ServletContainer servletContainer = servletContext.createServlet(org.glassfish.jersey.servlet.ServletContainer.class);
        ServletRegistration.Dynamic dynamic = servletContext.addServlet(SERVLET_NAME, servletContainer);
        dynamic.setInitParameter("javax.ws.rs.Application",resourceConfig);
        dynamic.setLoadOnStartup(1);
        dynamic.addMapping(config.getString("integration.jersey.base-path","/api")+"/*");

    }
    protected EnumSet<DispatcherType> getSecurityDispatcherTypes() {
        return EnumSet.of(DispatcherType.REQUEST, DispatcherType.ERROR, DispatcherType.ASYNC);
    }

    protected boolean isAsyncSecuritySupported() {
        return true;
    }
}
