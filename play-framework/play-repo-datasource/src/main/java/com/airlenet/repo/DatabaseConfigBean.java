package com.airlenet.repo;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by lig on 17/2/4.
 */
@Configuration
@ImportResource("classpath:applicationContext-druidStatInterceptor.xml")
public class DatabaseConfigBean {
    @Value("${jdbc.driver_class?:com.mysql.jdbc.Driver}")
    private String jdbcDriverClass;

    @Value("${jdbc.url?:jdbc:mysql://localhost:3306/play?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&failOverReadOnly=false&useSSL=false}")
    private String jdbcUrl;

    @Value("${jdbc.username?:root}")
    private String jdbcUsername;

    @Value("${jdbc.password?:123456}")
    private String jdbcPassword;

    @Value("${jdbc.multiple?:false}")
    private Boolean multipleDataSource;

    @Value("${druid.filters?:stat,wall}")
    private String filters;

    @Bean
    public DataSource dataSource() throws SQLException {
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

    @Bean(destroyMethod = "close",initMethod = "init")
    public DataSource mainDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(jdbcDriverClass);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(jdbcUsername);
        dataSource.setPassword(jdbcPassword);
        /** 配置初始化大小、最小、最大*/
        dataSource.setInitialSize(1);
        dataSource.setMinIdle(1);
        dataSource.setMaxActive(20);
        /**配置获取连接等待超时的时间*/
        dataSource.setMaxWait(60000);
        /**配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒*/
        dataSource.setTimeBetweenEvictionRunsMillis(60000);

        /**配置一个连接在池中最小生存的时间，单位是毫秒*/
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(false);
        dataSource.setTestOnReturn(false);
        /**打开PSCache，并且指定每个连接上PSCache的大小*/
        dataSource.setPoolPreparedStatements(false);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        /**配置监控统计拦截的filters*/
        dataSource.setFilters(filters);

        return dataSource;
    }

}
