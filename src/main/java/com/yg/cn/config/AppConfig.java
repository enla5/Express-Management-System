package com.yg.cn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 用这个方法不需要在controller在写登录的controller
 */
@Configuration
public class AppConfig implements WebMvcConfigurer {
    //
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("index").setViewName("/index.jsp");
    }
}
