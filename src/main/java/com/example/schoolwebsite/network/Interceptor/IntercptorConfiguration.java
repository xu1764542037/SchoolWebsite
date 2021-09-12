package com.example.schoolwebsite.network.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootConfiguration
public class IntercptorConfiguration implements WebMvcConfigurer {
    @Autowired
    private WebInterceptor interceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")
//                //放行哪些原始域
//                .allowedOriginPatterns("*")
//                .allowedMethods("*")
//                .allowedHeaders("*")
//                .exposedHeaders("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截除登录、注册外的所有请求
//        registry.addInterceptor(interceptor);
    }
}
