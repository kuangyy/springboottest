package com.kykys.demo;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * PropertiesInitializer
 *
 * @description Properties 文件初始化处理
 */
public class PropertiesInitializer {
    /**
     * 将Properties文件中的值初始化到servletContext中
     *
     * @param servletContext
     */
    public static void init(ServletContext servletContext) {
        String rootPath = System.getProperty("catalina.home");
        String separator = System.getProperty("file.separator");

        if (rootPath != null && !rootPath.isEmpty()) {
            rootPath = System.getProperty("catalina.base");
        }
        if (rootPath != null && !rootPath.isEmpty()) {
            String path = rootPath + separator + "webconfigs" + separator + "a";
            File file = new File(path);
            if (file.exists()) {
                File fileList[] = file.listFiles();

                // 遍历文件夹下的properties文件，不支持子目录
                for (File propertiesFile : fileList) {
                    if (propertiesFile.getAbsolutePath().endsWith(".properties")) {
                        Properties properties = new Properties();
                        InputStream input = null;
                        try {
                            input = new FileInputStream(propertiesFile);
                            properties.load(input);
                            Set<Object> keys = properties.keySet();
                            for (Object key : keys) {
                                servletContext.setInitParameter(key.toString(), properties.get(key).toString());
                            }
                        } catch (IOException io) {
                        } finally {
                            if (input != null) {
                                try {
                                    input.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
