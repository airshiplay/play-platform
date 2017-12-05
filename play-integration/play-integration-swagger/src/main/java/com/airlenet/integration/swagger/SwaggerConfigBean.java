package com.airlenet.integration.swagger;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author airlenet
 * @version 2017-11-12
 */

@Configuration
public class SwaggerConfigBean  extends WebMvcConfigurerAdapter implements InitializingBean {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/swagger-ui").addResourceLocations("classpath:/META-INF/swagger-ui/index.html").setCachePeriod(60 * 60 * 24 * 30);
        registry.addResourceHandler("/swagger-ui/").addResourceLocations("classpath:/META-INF/swagger-ui/index.html").setCachePeriod(60 * 60 * 24 * 30);
        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/swagger-ui/").setCachePeriod(60 * 60 * 24 * 30);
    }
    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
