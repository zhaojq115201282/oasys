package com.jikui.oasys.config;

import com.jikui.oasys.interceptor.LogInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: zhaojq
 * @Description:spring mvc 配置，系统第一次登陆，则将页面重定向到登录页面
 * @Date:Create：in 2020/6/20 17:10
 * @Modified By：
 **/
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Bean
    public LogInterceptor setBean(){
        return new LogInterceptor(); // 注入spring
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/manager/**")
//                .excludePathPatterns("/static/**","/","/manager/login");
        registry.addInterceptor(setBean()).addPathPatterns("/manager/**")
                .excludePathPatterns("/static/**","/","/manager/login");
    }
}
