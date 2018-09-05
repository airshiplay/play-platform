package com.airlenet.data.jpa.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix="play.jpa.json", value="enabled", matchIfMissing = false)
public class JpaJsonConfigBean implements InitializingBean {

	@Autowired
	private ObjectMapper objectMapper;

	@Bean
	public JpaModule jpaModule() {
		return new JpaModule();
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		objectMapper.registerModule(jpaModule());
	}

}
