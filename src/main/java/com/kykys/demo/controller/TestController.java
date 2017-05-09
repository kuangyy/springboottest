package com.kykys.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kuangye on 2017/4/24.
 */
@RestController
public class TestController {

//    @CrossOrigin
    @ApiOperation("/")
    @GetMapping
    public JSONObject test() {
        return JSON.parseObject("{\"a\":\"1\"}");
    }


}
