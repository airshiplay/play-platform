package com.airshiplay.play.template.thymeleaf.dialect;

import java.util.Set;

import org.thymeleaf.dialect.IProcessorDialect;
import org.thymeleaf.processor.IProcessor;

public class PlayDialect implements IProcessorDialect {
	public static final String PREFIX = "pl";
	public static final String NAME = "PlayDialect";
	public static final int PRECEDENCE = 1000;

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public String getPrefix() {
		return PREFIX;
	}

	@Override
	public int getDialectProcessorPrecedence() {
		return PRECEDENCE;
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		
		return null;
	}

}
