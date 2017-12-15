package com.airlenet.repo;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;


@EnableTransactionManagement
@Configuration
public class TransactionConfigBean {

    @Autowired
    ObjectFactory<DataSource> dataSource;

    @Bean
    PlatformTransactionManager transactionManager(){
        org.springframework.jdbc.datasource.DataSourceTransactionManager transactionManager =new org.springframework.jdbc.datasource.DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource.getObject());
        return transactionManager;
    }
}
