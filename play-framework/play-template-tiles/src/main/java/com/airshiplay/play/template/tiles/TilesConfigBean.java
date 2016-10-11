package com.airshiplay.play.template.tiles;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;

@Configuration
public class TilesConfigBean {

	@Autowired
	private ServletContext servletContext;

//	@Bean
//	public TilesViewResolver thymeleafViewResolver() {
//		TilesViewResolver resolver = new TilesViewResolver();
//		resolver.setSuffix(".jsp");
//		resolver.setViewNames(new String[] { "/views/*" });
//		resolver.setOrder(1);
//		resolver.setCache(false);
//
//		return resolver;
//	}
	@Bean
	public UrlBasedViewResolver viewResolver() {
	    UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
	    viewResolver.setViewClass(TilesView.class);
	    viewResolver.setOrder(0);
	    viewResolver.setViewNames(new String[] { "/views/*" });
	    viewResolver.setPrefix("/WEB-INF");
	    viewResolver.setSuffix(".jsp");
	    viewResolver.setCache(false);
	    return viewResolver;
	}
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions("classpath*:META-INF/**/tiles*.xml");


		return configurer;
	}

}
