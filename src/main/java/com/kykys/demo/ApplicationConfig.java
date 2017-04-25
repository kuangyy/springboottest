package com.kykys.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * Created by kuangye on 2017/4/24.
 */

@SpringBootApplication
public class ApplicationConfig extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationConfig.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        // property init
		PropertiesInitializer.init(servletContext);
        super.onStartup(servletContext);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfig.class, args);
    }
}
