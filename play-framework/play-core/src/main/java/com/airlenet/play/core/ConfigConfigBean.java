package com.airlenet.play.core;

import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置项配置
 *
 */
@Configuration
public class ConfigConfigBean {

	@Bean
	public static PlaceholderConfigurerSupport placeholderConfigurer() {
		ConfigurationPropertyResourceConfigurer placeholderConfigurer = new ConfigurationPropertyResourceConfigurer(
				StaticConfigSupplier.getConfiguration());
		placeholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return placeholderConfigurer;
	}

}
