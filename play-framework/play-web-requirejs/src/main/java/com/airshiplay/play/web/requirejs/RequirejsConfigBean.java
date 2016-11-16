package com.airshiplay.play.web.requirejs;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.airshiplay.play.filestorage.FileStorageService;

@Configuration
public class RequirejsConfigBean extends WebMvcConfigurerAdapter {
	@Autowired
	FileStorageService fileStorageService;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/requirejs/assets/**").addResourceLocations("classpath:/META-INF/assets/")
				.setCachePeriod(60 * 60 * 24 * 30);
		registry.addResourceHandler("/upload/**").addResourceLocations("file://"+new File(fileStorageService.getUploadDir()).getAbsolutePath()+"/")
		.setCachePeriod(60 * 60 * 24 * 30);
	}

}
