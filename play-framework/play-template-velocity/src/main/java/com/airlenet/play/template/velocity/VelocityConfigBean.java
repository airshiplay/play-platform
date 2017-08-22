package com.airlenet.play.template.velocity;

import java.util.Properties;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

@Configuration
public class VelocityConfigBean {

	@Autowired
	private ServletContext servletContext;

	@Value("${template.velocity.encoding?:UTF-8}")
	private String encoding;

	@Bean
	public VelocityViewResolver thymeleafViewResolver() {
		VelocityViewResolver resolver = new VelocityViewResolver();
		resolver.setSuffix(".vm");
		resolver.setViewNames(new String[] { "/views/*" });
		// resolver.setTemplateEngine(templateEngine());
		resolver.setContentType("text/html;charset=UTF-8");
		resolver.setOrder(1);
		resolver.setCache(false);

		return resolver;
	}

	@Bean
	public VelocityConfigurer configurer() {
		VelocityConfigurer configurer = new VelocityConfigurer();
		configurer.setResourceLoaderPath("/views");
		Properties velocityProperties = new Properties();
		velocityProperties.put("input.encoding", encoding);
		velocityProperties.put("output.encoding", encoding);
		velocityProperties.put("directive.foreach.counter.name", "velocityCount");//计数器名称
		velocityProperties.put("directive.foreach.counter.initial.value", 1);//计数器初始值
		velocityProperties.put("directive.foreach.maxloops", -1);//最大循环次数，-1为默认不限制 directive.foreach.iterator.name = velocityHasNex //迭代器名称

		
		configurer.setVelocityProperties(velocityProperties);

		return configurer;
	}

}
