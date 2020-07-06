package com.config;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板的配置
 */
@Data
@Accessors(chain = true)
public class TemplateConfig {

    /**模板别名*/
    private String name;

    /**存放的包名*/
    private String packageName;

    /**文件名*/
    private String fileName;

    /**模板的路径*/
    private String templateClassPath;

    /**如果使用自己写的模板  但是想传递一些配置  可以添加到这个Map中*/
    private Map<String, String> propertyMap;


    public TemplateConfig putProperty(String key, String value){
        if (propertyMap == null) {
            propertyMap = new HashMap<>(8);
        }
        propertyMap.put(key, value);
        return this;
    }

}
