package com.airlenet.auth;

import com.airlenet.admin.client.UserClient;
import com.airlenet.core.FullBeanNameGenerator;
import com.airlenet.web.LifeCyclePostProcessor;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;

//import com.airlenet.web.logging.aop.EnableAccessLogger;

@SpringBootApplication
//@EnableAccessLogger
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = UserClient.class)
@ComponentScan(nameGenerator = FullBeanNameGenerator.class)
public class PlayAuthCenterProviderApplication {

    @Bean
    @Order
    public LifeCyclePostProcessor lifeCyclePostProcessor(){
        return new LifeCyclePostProcessor();
    }
    public static void main(String[] args){
        SpringApplication.run(PlayAuthCenterProviderApplication.class,args);
    }
}
