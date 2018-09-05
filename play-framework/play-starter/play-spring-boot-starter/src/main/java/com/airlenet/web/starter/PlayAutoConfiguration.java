package com.airlenet.web.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author airlenet
 * @since 3.0.0
 */
@Configuration
@EnableConfigurationProperties(AppProperties.class)
//@ComponentScan("com.airlenet")
public class PlayAutoConfiguration {

}
