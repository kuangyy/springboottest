package com.kykys.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.kykys.demo.data.kykys.MottoModelMapper;
import com.kykys.demo.model.kykys.MottoModel;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by kuangye on 2017/4/24.
 */
@RestController
@RequestMapping("/motto")
public class MottoController {

    @Resource
    MottoModelMapper mottoModelMapper;

    @GetMapping("/list")
    public JSONObject list() {
        JSONObject jsonObject = new JSONObject();

        PageHelper.startPage(1, 1);
        List<MottoModel> mottoModelList = mottoModelMapper.selectByPage();
        long total = PageHelper.count(()->mottoModelMapper.selectByPage());
        jsonObject.put("list", mottoModelList);

        return jsonObject;
    }

    @PostMapping("/add")
    public JSONObject add(@RequestBody MottoModel mottoModel) {
        JSONObject jsonObject = new JSONObject();

        int i = mottoModelMapper.insertSelective(mottoModel);
        jsonObject.put("message", i > 0);

        return jsonObject;
    }

    @PutMapping("/update")
    public JSONObject update(@RequestBody MottoModel mottoModel) {
        JSONObject jsonObject = new JSONObject();

        int i = mottoModelMapper.updateByPrimaryKeySelective(mottoModel);
        jsonObject.put("message", i > 0);

        return jsonObject;
    }

    @DeleteMapping("/delete/{id}")
    public JSONObject delete(@PathVariable("id") Long id) {
        JSONObject jsonObject = new JSONObject();

        int i = mottoModelMapper.deleteByPrimaryKey(id);
        jsonObject.put("message", i > 0);

        return jsonObject;
    }

}
