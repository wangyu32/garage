package com.gxc15090111;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@MapperScan({"com.gxc15090111.garage.mapper","com.gxc15090111.system.mapper"})
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
