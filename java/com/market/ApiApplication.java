package com.market;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan(basePackages = {"com.market.dao"})
public class ApiApplication extends SpringBootServletInitializer {

    public static final boolean IS_DEBUG = false;
	public static final String DEBUG_USER_ID="37";

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(ApiApplication.class);
		springApplication.run(args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApiApplication.class);
	}
}
