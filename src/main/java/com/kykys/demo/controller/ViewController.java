package com.kykys.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Locale;

/**
 * Created by kuangye on 2017/4/24.
 */
@Controller
public class ViewController {

    @Autowired
    private MessageSource messageSource;

    @ApiOperation("/hi")
    @GetMapping("/hi")
    public ModelAndView test() {

        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("message", null, locale);

        String a = messageSource.getMessage("a", null, locale);
        System.out.println(a);
        return new ModelAndView("index").addObject("message", message);
    }

}
