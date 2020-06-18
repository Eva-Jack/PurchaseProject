package com.example.ego.manager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {
    /**
     * 实现WebMvcConfigurer接口的处理view的方法
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/item-list").setViewName("item-list");
        registry.addViewController("/item-add").setViewName("item-add");
        registry.addViewController("/item-param-list").setViewName("item-param-list");
        registry.addViewController("/item-param-add").setViewName("item-param-add");
        registry.addViewController("/content-category").setViewName("content-category");
        registry.addViewController("/content").setViewName("content");
        registry.addViewController("/content-add").setViewName("content-add");
        registry.addViewController("/content-edit").setViewName("content-edit");

    }

    /**
     * 放开静态资源访问
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
    }
}
