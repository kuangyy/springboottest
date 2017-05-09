package com.kykys.demo.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by kuangye on 2017/4/24.
 */
@Controller
public class ViewController {

    @ApiOperation("/hi")
    @GetMapping("/hi")
    public ModelAndView test(){
        return new ModelAndView("index").addObject("message","hi");
    }

}
