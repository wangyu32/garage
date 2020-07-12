package com.wangyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan({"com.wangyu.mapper"})
@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.wangyu"})
public class StartupApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(StartupApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(StartupApplication.class);
    }
}
