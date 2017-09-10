package com.airlenet.play.repo.mybatis;

import com.airlenet.play.plugins.mybatis.CameHumpInterceptor;
import com.airlenet.play.repo.mybatis.scanner.MapperScanner;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import javax.sql.DataSource;
import java.util.Properties;


@Configuration
//@MapperScan({"com.airshiplay.play.*.mapper","com.airlenet.play.*.mapper"})
@MapperScanner(basePackages ={"${mybatis.mapperScanner.basePackage?:com.airlenet.play.*.mapper}"}, sqlSessionFactoryRef = "sqlSessionFactory")
public class MybatisConfigBean {
    @Value("${mybatis.packages_scan?:default}")
    private String packageScan;

    @Value("${mybatis.packages_mapper?:default}")
    private String mapperScan;

    @Autowired
    private DataSource dataSource;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        org.mybatis.spring.SqlSessionFactoryBean factory = new org.mybatis.spring.SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        FileSystemXmlApplicationContext loader = new FileSystemXmlApplicationContext();
        factory.setMapperLocations(loader.getResources("classpath*:com.airlenet.play.*.mapper/*.xml,classpath*:mapper/*.xml,"+mapperScan));
        factory.setTypeAliasesPackage("com.airlenet.play.*.model,"+packageScan);
        com.github.pagehelper.PageHelper pageHelper = new com.github.pagehelper.PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("reasonable", "true");
        properties.setProperty("pageSizeZero", "true");
        pageHelper.setProperties(properties);
        factory.setPlugins(new Interceptor[]{pageHelper, new CameHumpInterceptor()});
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
