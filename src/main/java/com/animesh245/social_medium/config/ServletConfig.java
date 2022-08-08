package com.animesh245.social_medium.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan(basePackages = {"com.animesh245.social_medium.controller"})
public class ServletConfig implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/",".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        //CSS files
        registry.addResourceHandler("/css/**") //relative paths
                .addResourceLocations("/WEB-INF/resources/css/"); // actual path

        //Image files
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/WEB-INF/resources/images/", "file:///"+ Properties.WRITE_PATH);
    }

}
