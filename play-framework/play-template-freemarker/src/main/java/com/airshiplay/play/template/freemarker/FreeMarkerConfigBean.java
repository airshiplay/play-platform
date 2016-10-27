package com.airshiplay.play.template.freemarker;

import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

@Configuration
public class FreeMarkerConfigBean {

	@Autowired
	private ServletContext servletContext;

	@Value("${template.freemarker.encoding?:UTF-8}")
	private String encoding;

	@Value("${template.freemarker.supportShiro?:true}")
	private boolean supportShiro;
	
	@Bean
	public FreeMarkerViewResolver freeMarkerViewResolver() {
		FreeMarkerViewResolver resolver = new FreeMarkerViewResolver();
		resolver.setSuffix(".ftl");
		resolver.setViewNames(new String[] { "/views/*" });
		resolver.setContentType("text/html;charset=UTF-8");
		resolver.setOrder(0);
		resolver.setCache(false);
		resolver.setRequestContextAttribute("request");
		resolver.setExposeRequestAttributes(true);
		resolver.setExposeSessionAttributes(true);
		resolver.setExposeSpringMacroHelpers(true);
		return resolver;
	}

	@Bean
	public FreeMarkerConfigurer configurer() {
		FreeMarkerConfigurer configurer;
		if(supportShiro){
			configurer = new ShiroTagFreeMarkerConfigurer();
		}else{
			configurer = new FreeMarkerConfigurer();
		}	 
		configurer.setTemplateLoaderPath("classpath:/");
		configurer.setDefaultEncoding("utf-8");
		Properties settings = new Properties();
		configurer.setFreemarkerSettings(settings);
		configurer.setPreferFileSystemAccess(false);	
		return configurer;
	}

}
