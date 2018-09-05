package com.airlenet.web;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;

import java.util.List;
@Slf4j
public class SpringBootLifeCyclePostProcessor implements ApplicationListener<SpringApplicationEvent> {

	@Override
	public void onApplicationEvent(SpringApplicationEvent springApplicationEvent) {
		if(springApplicationEvent instanceof ApplicationEnvironmentPreparedEvent){

		}else if(springApplicationEvent instanceof ApplicationStartingEvent){

		}else if(springApplicationEvent instanceof ApplicationStartedEvent){

		}else if(springApplicationEvent instanceof ApplicationReadyEvent){

		}else if(springApplicationEvent instanceof ApplicationPreparedEvent){

		}

	}
}
