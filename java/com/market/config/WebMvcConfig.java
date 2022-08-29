package com.market.config;


import com.market.intercepter.AuthorizeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by Elias on 2019/4/20
 */
//@Profile("dev")
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthorizeInterceptor authorizeInterceptor;

    @Autowired
    public WebMvcConfig(AuthorizeInterceptor authorizeInterceptor) {
        this.authorizeInterceptor = authorizeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizeInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/static/**","/user/error","/user/login1");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }




}
