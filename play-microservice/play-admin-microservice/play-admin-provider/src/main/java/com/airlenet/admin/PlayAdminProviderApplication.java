package com.airlenet.admin;

import com.airlenet.core.FullBeanNameGenerator;
import com.airlenet.data.jpa.EntityRepository;
import com.airlenet.data.jpa.EntityRepositoryFactoryBean;
import com.airlenet.web.LifeCyclePostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EnableJpaRepositories(basePackages = {"com.airlenet.admin"}, includeFilters = {
//        @ComponentScan.Filter(value = EntityRepository.class,
//                type = FilterType.ASSIGNABLE_TYPE)},
//        repositoryImplementationPostfix = "Impl",
//        repositoryFactoryBeanClass = EntityRepositoryFactoryBean.class)
@EnableDiscoveryClient
@EnableEurekaClient
@ComponentScan(basePackages = {"com.airlenet.data","com.airlenet.admin"},nameGenerator = FullBeanNameGenerator.class)
@EntityScan(basePackages = "com.airlenet.admin")
//@EnableJpaAuditing
public class PlayAdminProviderApplication {

    @Bean
    @Order
    public LifeCyclePostProcessor lifeCyclePostProcessor(){
        return new LifeCyclePostProcessor();
    }

    public static void main(String[] args){
        SpringApplication.run(PlayAdminProviderApplication.class,args);
    }
}
