package com.kykys.demo.data.config;

import java.util.Properties;

/**
 * Created by kuangye on 2017/5/9.
 */
public class MybatisPageHelperProperties {


    private static Properties properties = new Properties();

    static {
        //pageNum<=0 时会查询第一页， pageNum>pages（超过总数时），会查询最后一页。
        properties.put("reasonable", true);
        //分页会进行 count 查询。
        properties.put("rowBoundsWithCount", true);
    }


    public static Properties getProperties() {
        return properties;
    }

}
