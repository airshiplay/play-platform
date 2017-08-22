package com.airshiplay.play.main.mvc;

import com.airshiplay.play.main.service.ServiceSelector;
import com.airshiplay.play.web.ServletSupport;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;
import org.springframework.web.method.annotation.ModelAttributeMethodProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

@ServletSupport
@Component
public class ArgumentResolverResorter implements InitializingBean {

	@Autowired
	@Qualifier("mvcConversionService")
	private ObjectFactory<ConversionService> conversionService;

	@Autowired
	private ObjectFactory<ServiceSelector> serviceSelector;

	@Autowired
	private RequestMappingHandlerAdapter handlerAdapter;

	@Override
	public void afterPropertiesSet() throws Exception {
		List<HandlerMethodArgumentResolver> argumentResolvers = handlerAdapter.getArgumentResolvers();
		List<HandlerMethodArgumentResolver> newArgumentResolvers = new ArrayList<>(argumentResolvers);
		int indexArg = findIndexOfModelAttributeMethodProcessor(argumentResolvers);
		newArgumentResolvers.set(indexArg, new EntityModelAttributeMethodProcessor(serviceSelector.getObject(),
				conversionService.getObject(), false));
		handlerAdapter.setArgumentResolvers(newArgumentResolvers);

		List<HandlerMethodArgumentResolver> initBinderArgumentResolvers = handlerAdapter
				.getInitBinderArgumentResolvers();
		List<HandlerMethodArgumentResolver> newInitBinderArgumentResolvers = new ArrayList<>(
				initBinderArgumentResolvers);
		int indexBinder = findIndexOfModelAttributeMethodProcessor(handlerAdapter.getInitBinderArgumentResolvers());
		newInitBinderArgumentResolvers.set(indexBinder, new EntityModelAttributeMethodProcessor(
				serviceSelector.getObject(), conversionService.getObject(), false));
		handlerAdapter.setInitBinderArgumentResolvers(newInitBinderArgumentResolvers);
	}

	private int findIndexOfModelAttributeMethodProcessor(List<HandlerMethodArgumentResolver> resolvers) {
		for (int i = 0; i < resolvers.size(); i++) {
			HandlerMethodArgumentResolver resolver = resolvers.get(i);
			if (resolver instanceof ModelAttributeMethodProcessor) {
				return i;
			}
		}

		return -1;
	}

}
