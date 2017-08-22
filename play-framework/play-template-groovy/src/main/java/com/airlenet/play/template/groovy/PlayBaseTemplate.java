package com.airlenet.play.template.groovy;

import java.util.Map;

import com.airlenet.play.core.ConfigWrapper;
import com.airlenet.play.core.StaticConfigSupplier;
import com.airlenet.play.web.WebSpringContext;

import groovy.text.markup.BaseTemplate;
import groovy.text.markup.MarkupTemplateEngine;
import groovy.text.markup.TemplateConfiguration;

public abstract class PlayBaseTemplate extends BaseTemplate {

	public PlayBaseTemplate(MarkupTemplateEngine templateEngine, Map<String, Object> model,
			Map<String, String> modelTypes, TemplateConfiguration configuration) {
		super(templateEngine, model, modelTypes, configuration);

		model.put("base", WebSpringContext.getContextPath());
		model.put("staticConfig", new ConfigWrapper(StaticConfigSupplier.getConfiguration()));
	}

}
