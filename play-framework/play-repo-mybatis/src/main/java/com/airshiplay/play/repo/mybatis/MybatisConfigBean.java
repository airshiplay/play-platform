package com.airshiplay.play.repo.mybatis;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
@MapperScan("com.airshiplay.play.*.mapper")
public class MybatisConfigBean {
    @Autowired
    DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        org.mybatis.spring.SqlSessionFactoryBean factory = new org.mybatis.spring.SqlSessionFactoryBean();
        factory.setDataSource(dataSource);

        FileSystemXmlApplicationContext loader = new FileSystemXmlApplicationContext();
        // new Resource[] { new ClassPathResource("mapper/*.xml"), new
        // ClassPathResource("mapper/custom/*.xml") }
        factory.setMapperLocations(loader.getResources("classpath*:mapper/*.xml"));
        factory.setTypeAliasesPackage("com.airshiplay.play.*.model");// bean
        com.github.pagehelper.PageHelper pageHelper = new com.github.pagehelper.PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(properties);
        factory.setPlugins(new Interceptor[]{pageHelper});
        return factory.getObject();
    }


//    @Bean
//    @Lazy
//    public org.mybatis.spring.mapper.MapperScannerConfigurer MapperScannerConfigurer() {
//        MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
//        scannerConfigurer.setBasePackage("com.airshiplay.play.app.mapper");// mapper
//        //scannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        return scannerConfigurer;
//    }
}
