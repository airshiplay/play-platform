package com.airlenet.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@SessionAttributes("authorizationRequest")
public class WebMvcConfig  implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("/login");
//        registry.addViewController("/oauth/confirm_access").setViewName("authorize");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
