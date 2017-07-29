package com.naiden.digitallab.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class DefaultView extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers( ViewControllerRegistry registry ) {

        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
//        registry.addViewController( "/" ).setViewName( "forward:/home.html" );
//        regisry.setOrder( Ordered.HIGHEST_PRECEDENCE );
//        super.addViewControllers( registry );
    }
}