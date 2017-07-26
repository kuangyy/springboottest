package com.kykys.demo.i18n;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

/*
 * MessageSourceConfig.java 1.0.0 2017/7/25  18:25 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/7/25  18:25 created by ky
 */
@Configuration
public class MessageSourceConfig {

    @Value(value = "${spring.messages.basenames}")
    private String basenames;
    @Value(value = "${spring.messages.encoding}")
    private String defaultEncoding;

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource reloadableResourceBundleMessageSource = new ResourceBundleMessageSource();
        reloadableResourceBundleMessageSource.setBasenames(basenames.split(","));
        reloadableResourceBundleMessageSource.setDefaultEncoding(defaultEncoding);
        return reloadableResourceBundleMessageSource;
    }
}
