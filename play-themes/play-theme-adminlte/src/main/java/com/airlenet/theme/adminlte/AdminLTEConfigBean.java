package com.airlenet.theme.adminlte;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.airlenet.theme.adminlte.ztree.ZTreeLazyList;
import com.airlenet.theme.adminlte.ztree.ZTreeLazyListSerializer;
import com.airlenet.theme.adminlte.ztree.ZTreeNode;
import com.airlenet.theme.adminlte.ztree.ZTreeNodeSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Configuration
public class AdminLTEConfigBean extends WebMvcConfigurerAdapter implements InitializingBean {

	@Autowired
	private ObjectFactory<ObjectMapper> objectMapper;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/adminlte/**").addResourceLocations("classpath:/META-INF/adminlte/").setCachePeriod(60 * 60 * 24 * 30);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addSerializer(ZTreeNode.class, new ZTreeNodeSerializer<>());
		simpleModule.addSerializer(ZTreeLazyList.class, new ZTreeLazyListSerializer<>());
		objectMapper.getObject().registerModule(simpleModule);
	}

}
