package com.example.schoolwebsite.network.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class FilterConfiguration {


    @Autowired
    private WebFilter webFilter;

    @Bean
    public FilterRegistrationBean<WebFilter> FilterRegister(){
        FilterRegistrationBean<WebFilter> Reg = new FilterRegistrationBean<>();
        Reg.setFilter(webFilter);
        Reg.addUrlPatterns("/*");
        Reg.setName("UserFilter");
        return Reg;
    }
}
