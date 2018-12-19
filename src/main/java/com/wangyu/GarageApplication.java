package com.wangyu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@MapperScan({"com.wangyu.garage.mapper","com.wangyu.prm.mapper"})
@SpringBootApplication
public class GarageApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GarageApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return super.configure(builder);
        return builder.sources(GarageApplication.class);
    }
}
