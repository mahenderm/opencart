package com.centurylink.pctl.mod.ui.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * Created by Begin Samuel
 */
@Configuration
public class ViewConfigurer extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
            .addResourceHandler("/**")
            .addResourceLocations("classpath:/static/")
            .setCachePeriod((int) TimeUnit.HOURS.toSeconds(24L));
    }
}
