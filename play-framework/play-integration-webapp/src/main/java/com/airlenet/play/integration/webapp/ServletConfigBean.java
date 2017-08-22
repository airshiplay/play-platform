package com.airlenet.play.integration.webapp;

import org.springframework.beans.factory.config.PlaceholderConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.airlenet.play.core.ConfigurationPropertyResourceConfigurer;
import com.airlenet.play.core.StaticConfigSupplier;
import com.airlenet.play.web.ServletSupport;

@Configuration
@ComponentScan(basePackages = { "com.airshiplay","com.airlenet" }, useDefaultFilters = false, includeFilters = {
		@Filter({ Controller.class }), @Filter({ ServletSupport.class }) }, nameGenerator = FullBeanNameGenerator.class)
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass=true)

public class ServletConfigBean {

	@Bean
	public static PlaceholderConfigurerSupport placeholderConfigurer() {
		return new ConfigurationPropertyResourceConfigurer(StaticConfigSupplier.getConfiguration());
	}
}
