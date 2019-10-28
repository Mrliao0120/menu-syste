package com.menu.config;


import com.menu.interceptor.ParameterInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ProjectName movies_box
 * @Author LHB
 * @Data 2019/5/24 10:24
 * @Version 1.0
 * @Description 拦截器 拦截参数相关
 */
@Configuration
@Order
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    ParameterInterceptor parameterInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ParameterInterceptor()).addPathPatterns("/**");
        /*registry.addInterceptor(new LoginAccountInterceptor()).addPathPatterns("/backstage/**")
                .excludePathPatterns("/backstage/user/login");*/
    }
}
