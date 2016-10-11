package com.airshiplay.play.template.groovy;

import java.util.Map;

import com.airshiplay.play.core.ConfigWrapper;
import com.airshiplay.play.core.StaticConfigSupplier;
import com.airshiplay.play.web.WebSpringContext;

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
