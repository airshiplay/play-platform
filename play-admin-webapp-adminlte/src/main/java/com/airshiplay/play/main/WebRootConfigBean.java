package com.airshiplay.play.main;



import com.airlenet.repo.jpa.EntityRepository;
import com.airlenet.repo.jpa.EntityRepositoryFactoryBean;
import com.airlenet.web.ServletSupport;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author airlenet
 * @version 2017-09-11
 */
@Configuration
@ComponentScan(basePackages = { "com.airshiplay" }, excludeFilters = { @ComponentScan.Filter(value = Controller.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = EnableWebMvc.class, type = FilterType.ANNOTATION),
        @ComponentScan.Filter(value = ServletSupport.class, type = FilterType.ANNOTATION) })
@EnableJpaRepositories(basePackages = { "com.airshiplay" }, includeFilters = {
        @Filter(value = EntityRepository.class, type = FilterType.ASSIGNABLE_TYPE) }, repositoryImplementationPostfix = "Impl", repositoryFactoryBeanClass = EntityRepositoryFactoryBean.class)
public class WebRootConfigBean {

}