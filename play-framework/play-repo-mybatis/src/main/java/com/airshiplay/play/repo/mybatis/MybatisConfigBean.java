package com.airshiplay.play.repo.mybatis;

import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
public class MybatisConfigBean {
	@Value("${jdbc.driver_class?:com.mysql.jdbc.Driver}")
	private String jdbcDriverClass;

	@Value("${jdbc.url?:jdbc:mysql://localhost:3306/mdm?useUnicode=true&characterEncoding=utf-8&useSSL=false}")
	private String jdbcUrl;

	@Value("${jdbc.username?:root}")
	private String jdbcUsername;

	@Value("${jdbc.password?:123456}")
	private String jdbcPassword;

	@Bean
	public DataSource dataSource() throws SQLException {
		return (mainDataSource());
	}

	@Bean(destroyMethod = "close")
	public DataSource mainDataSource() throws SQLException {
		DruidDataSource dataSource = new com.alibaba.druid.pool.DruidDataSource();
		dataSource.setDriverClassName(jdbcDriverClass);
		dataSource.setUrl(jdbcUrl);
		dataSource.setUsername(jdbcUsername);
		dataSource.setPassword(jdbcPassword);
		dataSource.setFilters("stat");
		dataSource.setMaxActive(20);
		dataSource.setInitialSize(1);
		dataSource.setMaxWait(60000);
		dataSource.setMinIdle(1);
		dataSource.setTimeBetweenEvictionRunsMillis(60000);
		dataSource.setMinEvictableIdleTimeMillis(300000);
		dataSource.setRemoveAbandoned(true);//<!-- 打开removeAbandoned功能 -->
		dataSource.setRemoveAbandonedTimeout(1800);//<!-- 1800秒，也就是30分钟 -->
		dataSource.setLogAbandoned(true);//<!-- 关闭abanded连接时输出错误日志 -->
		dataSource.setValidationQuery("SELECT 'x'");
		dataSource.setTestWhileIdle(true);
		dataSource.setTestOnBorrow(false);
		dataSource.setTestOnReturn(false);
//		dataSource.setProxyFilters(filters);
		return dataSource;
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		org.mybatis.spring.SqlSessionFactoryBean factory = new org.mybatis.spring.SqlSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setMapperLocations(new Resource[] {
				new ClassPathResource("mapper/*.xml"),
				new ClassPathResource("mapper/custom/*.xml") });
		factory.setTypeAliasesPackage("com.airshiplay.play");
		com.github.pagehelper.PageHelper pageHelper = new com.github.pagehelper.PageHelper();
		Properties properties = new Properties();
		properties.setProperty("dialect", "mysql");
		properties.setProperty("reasonable", "true");
		properties.setProperty("pageSizeZero", "true");
		pageHelper.setProperties(properties);
		factory.setPlugins(new Interceptor[] { pageHelper });
		return factory.getObject();
	}

	@Bean
	public org.mybatis.spring.mapper.MapperScannerConfigurer MapperScannerConfigurer() {
		MapperScannerConfigurer scannerConfigurer = new MapperScannerConfigurer();
		scannerConfigurer.setBasePackage("com.airshiplay.play");
		scannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
		return scannerConfigurer;
	}

	@Bean
	@Scope("prototype")
	public SqlSessionTemplate sqlSession() throws Exception {
		org.mybatis.spring.SqlSessionTemplate sqlSession = new org.mybatis.spring.SqlSessionTemplate(
				sqlSessionFactory());
		return sqlSession;

	}
}
