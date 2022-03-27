package com.base.controller.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Configuration
public class BaseRequestConfiguration extends WebMvcConfigurerAdapter {

    private HandlerInterceptorAdapter requestStartTimeSetterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestStartTimeSetterInterceptor);
    }

    @Autowired
    @Qualifier("requestStartTimeSetterInterceptor")
    public void setElapsedTimeInterceptor(HandlerInterceptorAdapter requestStartTimeSetterInterceptor) {
        this.requestStartTimeSetterInterceptor = requestStartTimeSetterInterceptor;
    }

}
