package com.airlenet.standalone;


import com.airlenet.admin.PlayAdminProviderApplication;
import com.airlenet.auth.PlayAuthCenterProviderApplication;
import com.airlenet.core.FullBeanNameGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication
@ComponentScan(nameGenerator = FullBeanNameGenerator.class,basePackages = "com.airlenet",
        excludeFilters = {@ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                value = {PlayAdminProviderApplication.class,PlayAuthCenterProviderApplication.class})})
@EntityScan(basePackages = "com.airlenet.admin")
public class AllInOneApplication {
    public static void main(String args[]){
        SpringApplication.run(AllInOneApplication.class,args);
    }
}
