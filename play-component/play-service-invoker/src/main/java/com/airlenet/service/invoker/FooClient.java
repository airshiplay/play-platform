package com.airlenet.service.invoker;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "play-service-provider", fallback = FooClientFallback.class)
public interface FooClient {

	@RequestMapping(value = "/call/{id}", method = RequestMethod.GET)
	public Foo call(@PathVariable("id") Integer id);
}
