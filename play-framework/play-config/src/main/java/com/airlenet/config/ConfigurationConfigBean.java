package com.airlenet.config;

import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置
 * 
 * @author airlenet
 *
 */
@Configuration
public class ConfigurationConfigBean {

	@Bean
	public static PlaceholderConfigurerSupport placeholderConfigurer() {
		ConfigurationPropertyResourceConfigurer placeholderConfigurer = new ConfigurationPropertyResourceConfigurer(
				StaticConfigSupplier.getConfiguration());
		placeholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
		return placeholderConfigurer;
	}

}
