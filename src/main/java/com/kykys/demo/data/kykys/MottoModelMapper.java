package com.kykys.demo.data.kykys;


import com.kykys.demo.data.KykysDB;
import com.kykys.demo.model.kykys.MottoModel;

import java.util.List;
import java.util.Map;

@KykysDB
public interface MottoModelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MottoModel record);

    int insertSelective(MottoModel record);

    MottoModel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MottoModel record);

    int updateByPrimaryKey(MottoModel record);

    MottoModel selectByRandom();

    List<MottoModel> selectByPage();

    int count(Map<String, ?> map);
}