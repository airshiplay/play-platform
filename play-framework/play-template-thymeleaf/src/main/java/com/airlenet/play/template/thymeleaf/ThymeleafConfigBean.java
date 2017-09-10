package com.airlenet.play.template.thymeleaf;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import com.airlenet.play.core.PlayConstants;
import com.airlenet.play.core.ConfigWrapper;
import com.airlenet.play.core.StaticConfigSupplier;
import com.airlenet.play.web.WebSpringContext;

@Configuration
public class ThymeleafConfigBean {

	@Autowired
	private ServletContext servletContext;

	@Value("${template.thymeleaf.cacheable?:true}")
	private Boolean cacheable;

	// servletcontext/classpath
	@Value("${template.thymeleaf.loader?:auto}")
	private String loader;

	@Value("${template.thymeleaf.prefix?:/WEB-INF/templates/}")
	private String prefix;

	@Value("${template.thymeleaf.class_prefix?:/META-INF/templates/}")
	private String classPrefix;

	@Bean
	public ThymeleafViewResolver thymeleafViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setOrder(1);
		resolver.setCharacterEncoding(PlayConstants.characterEncoding);
		resolver.addStaticVariable("base", WebSpringContext.getContextPath());
		resolver.addStaticVariable("staticConfig", new ConfigWrapper(StaticConfigSupplier.getConfiguration()));
		resolver.setExcludedViewNames(new String[] { "/views/*", "/jsp/*", "*.jsp", "*.ftl" });

		// resolver.addStaticVariable("auth", AuthVariable.getInstance());
		return resolver;
	}

	// SpringResourceTemplateResolver:classpath
	@Bean
	public ITemplateResolver templateResolver() {
		ITemplateResolver templateResolver = null;
		switch (loader) {
		case "servletcontext":
			templateResolver = servletContextTemplateResolver();
			break;
		case "classpath":
			templateResolver = classLoaderTemplateResolver();
			break;
		case "spring":
			templateResolver = new SpringResourceTemplateResolver();
			break;

		default:
			templateResolver = new AutoTemplateResolver(servletContextTemplateResolver(),
					classLoaderTemplateResolver());
			break;
		}
		return templateResolver;

	}

	protected ServletContextTemplateResolver servletContextTemplateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setPrefix(prefix);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode("HTML");
		templateResolver.setCacheable(cacheable);
		templateResolver.setCharacterEncoding(PlayConstants.characterEncoding);
		return templateResolver;
	}

	protected ClassLoaderTemplateResolver classLoaderTemplateResolver() {
		ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
		templateResolver.setPrefix(classPrefix);
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
