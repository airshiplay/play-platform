package com.airshiplay.play.template.thymeleaf;

import java.util.Map;

import javax.servlet.ServletContext;

import org.thymeleaf.IEngineConfiguration;
import org.thymeleaf.templateresolver.AbstractConfigurableTemplateResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresource.ClassLoaderTemplateResource;
import org.thymeleaf.templateresource.ITemplateResource;
import org.thymeleaf.templateresource.ServletContextTemplateResource;
import org.thymeleaf.util.ClassLoaderUtils;
import org.thymeleaf.util.Validate;

public class PlayTemplateResolver extends AbstractConfigurableTemplateResolver {

	private final ClassLoader classLoader;
	private final ServletContext servletContext;

	public PlayTemplateResolver(final ServletContext servletContext) {
		super();
		this.classLoader = ClassLoaderUtils.getClassLoader(ClassLoaderTemplateResolver.class);
		this.servletContext = servletContext;
		Validate.notNull(classLoader, "Class Loader cannot be null");
		Validate.notNull(servletContext, "ServletContext cannot be null");
	}

	@Override
	protected ITemplateResource computeTemplateResource(IEngineConfiguration configuration, String ownerTemplate, String template, String resourceName, String characterEncoding,
			Map<String, Object> templateResolutionAttributes) {
		if (template.startsWith("/bootstrap")) {			
			return new ClassLoaderTemplateResource(this.classLoader, resourceName.substring(getPrefix().length()), characterEncoding);
		} else {
			
			return new ServletContextTemplateResource(this.servletContext, resourceName, characterEncoding);
		}

	}

}
