package com.airshiplay.play.template.thymeleaf;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.airshiplay.play.core.PlayConstants;
import com.airshiplay.play.core.ConfigWrapper;
import com.airshiplay.play.core.StaticConfigSupplier;
import com.airshiplay.play.web.WebSpringContext;

@Configuration
public class ThymeleafConfigBean {

	@Autowired
	private ServletContext servletContext;

	@Value("${template.thymeleaf.cacheable?:true}")
	private Boolean cacheable;

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setOrder(1);
		resolver.setCharacterEncoding(PlayConstants.characterEncoding);
		resolver.addStaticVariable("base", WebSpringContext.getContextPath());
		resolver.addStaticVariable("staticConfig", new ConfigWrapper(StaticConfigSupplier.getConfiguration()));
		resolver.setExcludedViewNames(new String[] { "/views/*" });

		// resolver.addStaticVariable("auth", AuthVariable.getInstance());
		return resolver;
	}

	// SpringResourceTemplateResolver:classpath
	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setPrefix("/WEB-INF/templates/");
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCacheable(cacheable);
		templateResolver.setCharacterEncoding(PlayConstants.characterEncoding);
		return templateResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		// templateEngine.addDialect(new SpringDataDialect());
		// templateEngine.addDialect(new SpringSecurityDialect());
		return templateEngine;
	}
}
