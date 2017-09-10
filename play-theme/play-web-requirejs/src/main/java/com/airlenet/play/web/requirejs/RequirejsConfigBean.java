package com.airlenet.play.web.requirejs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class RequirejsConfigBean extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/requirejs/app/**").addResourceLocations("classpath:/META-INF/app/")
				.setCachePeriod(60 * 60 * 24 * 30);
	}

}
