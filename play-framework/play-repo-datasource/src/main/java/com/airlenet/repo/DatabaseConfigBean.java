package com.airlenet.repo;

import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by lig on 17/2/4.
 */
@Configuration
public class DatabaseConfigBean {
    @Value("${jdbc.driver_class?:com.mysql.jdbc.Driver}")
    private String jdbcDriverClass;

    @Value("${jdbc.url?:jdbc:mysql://localhost:3306/play?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false}")
    private String jdbcUrl;

    @Value("${jdbc.username?:root}")
    private String jdbcUsername;

    @Value("${jdbc.password?:123456}")
    private String jdbcPassword;

    @Value("${jdbc.multiple?:false}")
    private Boolean multipleDataSource;

    @Bean
    public DataSource dataSource() {
        DataSource mainDataSource = mainDataSource();
        if (multipleDataSource) {
            Map<Object, Object> targetDataSources = new HashMap<>();
            targetDataSources.put("main", mainDataSource);

            ThreadLocalDynamicDataSource dynamicDataSource = new ThreadLocalDynamicDataSource();
            dynamicDataSource.setTargetDataSources(targetDataSources);
            dynamicDataSource.setDefaultTargetDataSource(mainDataSource);

            return dynamicDataSource;
        }
        return new LazyConnectionDataSourceProxy(mainDataSource);
    }

    @Bean(destroyMethod = "close")
    public DataSource mainDataSource() {
        BoneCPDataSource dataSource = new BoneCPDataSource();
        dataSource.setDriverClass(jdbcDriverClass);
        dataSource.setJdbcUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        dataSource.setIdleConnectionTestPeriodInMinutes(2);
        dataSource.setIdleMaxAge(2, TimeUnit.HOURS);
        dataSource.setMaxConnectionsPerPartition(60);
        dataSource.setMinConnectionsPerPartition(20);
        dataSource.setPartitionCount(3);
        dataSource.setAcquireIncrement(10);
        dataSource.setStatementsCacheSize(50);
        return dataSource;
    }
}
