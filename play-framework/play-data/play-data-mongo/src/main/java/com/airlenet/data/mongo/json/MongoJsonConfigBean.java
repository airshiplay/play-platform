package com.airlenet.data.mongo.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MongoJsonConfigBean implements InitializingBean {

	@Autowired
	private ObjectMapper objectMapper;

	@Bean
	public MongoModule mongoModule() {
		return new MongoModule();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		objectMapper.registerModule(mongoModule());
	}

}
