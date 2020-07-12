package com.wangyu.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Slf4j
@Configuration
@MapperScan(basePackages = "com.wangyu.mapper", sqlSessionTemplateRef = "sqlSessionTemplate")
public class MyBatisConfig {

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    @Bean(name = "dataSource")
    @Qualifier("dataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }


    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        MybatisSqlSessionFactoryBean bean = new MybatisSqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        String classPath = "classpath*:mapper/*.xml";
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(classPath));
        //分页插件配置
        bean.setPlugins(new Interceptor[]{new PaginationInterceptor()});
        return bean.getObject();
    }

    @Bean(name = "transactionManager")
    @Primary
    public DataSourceTransactionManager testTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "sqlSessionTemplate")
    @Primary
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}


