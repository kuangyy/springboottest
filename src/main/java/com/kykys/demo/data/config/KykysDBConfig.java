package com.kykys.demo.data.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import com.kykys.demo.data.KykysDB;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by kuangye on 2017/4/24.
 */
@Configuration
@MapperScan(basePackages = "com.kykys.demo.data.kykys", annotationClass = KykysDB.class, sqlSessionFactoryRef = "kykysdbSqlSessionFactory")
public class KykysDBConfig {

    static final String MAPPER_LOCATION = "classpath*:mapper/kykys/*.xml";

    @Primary
    @Bean(name = "kykysdbDataSource")
    @ConfigurationProperties(prefix = "kykys.datasource")
    public DataSource kykysdbDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        return dataSource;
    }

    @Primary
    @Bean(name = "kykysdbTransactionManager")
    public DataSourceTransactionManager masterTransactionManager() {
        return new DataSourceTransactionManager(kykysdbDataSource());
    }

    @Primary
    @Bean(name = "kykysdbSqlSessionFactory")
    public SqlSessionFactory kykysdbSqlSessionFactory(@Qualifier("kykysdbDataSource") DataSource kykysdbDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(kykysdbDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(KykysDBConfig.MAPPER_LOCATION));

        //mybatis PageHelper
        Interceptor[] interceptors = new Interceptor[1];
        PageInterceptor pageInterceptor = new PageInterceptor();
        pageInterceptor.setProperties(MybatisPageHelperProperties.getProperties());
        interceptors[0] = pageInterceptor;
        sessionFactory.setPlugins(interceptors);

        return sessionFactory.getObject();
    }
}
