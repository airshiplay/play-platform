package com.airshiplay.play.main;

/**
 * @author airlenet
 * @version 2017-09-11
 */

import com.airlenet.integration.webapp.FullBeanNameGenerator;
import com.airlenet.web.ServletSupport;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = { "com.airshiplay" }, useDefaultFilters = false, includeFilters = { @Filter({ Controller.class, RestController.class}),
        @Filter({ ServletSupport.class }) }, nameGenerator = FullBeanNameGenerator.class)
@EnableWebMvc
public class WebServletConfigBean {

}