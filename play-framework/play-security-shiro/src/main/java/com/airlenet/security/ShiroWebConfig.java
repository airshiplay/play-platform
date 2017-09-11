package com.airlenet.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.airlenet.web.ServletSupport;


@Configuration
@ServletSupport
public class ShiroWebConfig extends WebMvcConfigurerAdapter {

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
      // equivalent to <mvc:argument-resolvers>
	  argumentResolvers.add( currentUserResolver());
  }


  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
      // equivalent to <mvc:message-converters>
  }
  @Bean
  public CurrentUserMethodArgumentResolver currentUserResolver(){
	  return new CurrentUserMethodArgumentResolver();
  }
}