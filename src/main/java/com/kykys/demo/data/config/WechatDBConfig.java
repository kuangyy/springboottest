package com.kykys.demo.data.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.kykys.demo.data.WechatDB;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * Created by kuangye on 2017/4/24.
 */
@Configuration
@MapperScan(basePackages = "com.kykys.data",annotationClass = WechatDB.class,sqlSessionFactoryRef = "wechatdbSqlSessionFactory")
public class WechatDBConfig {

    static final String MAPPER_LOCATION = "classpath*:wechat/*.xml";

    @Bean(name = "wechatdbDataSource")
    @ConfigurationProperties(prefix = "wechat.datasource")
    public DataSource wechatdbDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Bean(name = "wechatdbTransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(wechatdbDataSource());
    }

    @Bean(name = "wechatdbSqlSessionFactory")
    public SqlSessionFactory centerdbSqlSessionFactory(@Qualifier("wechatdbDataSource") DataSource wechatdbDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(wechatdbDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(WechatDBConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }
}
