package com.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取通用模板的简单工厂
 */
public class SimpleTemplateFactory {

    /**存放config*/
    private static Map<String, TemplateConfig> configMap;

    static {
        configMap = new HashMap<>();
        //添加基本的模板
        configMap.put("entity", new TemplateConfig().setName("entity").setPackageName("entity").setFileName("$").setTemplateClassPath("templates/entity.java.vm"));
        configMap.put("dao", new TemplateConfig().setName("dao").setPackageName("dao").setFileName("$Dao").setTemplateClassPath("templates/dao.java.vm"));
        configMap.put("service", new TemplateConfig().setName("service").setPackageName("service").setFileName("$Service").setTemplateClassPath("templates/service.java.vm"));
        configMap.put("serviceImpl", new TemplateConfig().setName("serviceImpl").setPackageName("service.impl").setFileName("$ServiceImpl").setTemplateClassPath("templates/serviceImpl.java.vm"));
        configMap.put("controller", new TemplateConfig().setName("controller").setPackageName("controller").setFileName("$Controller").setTemplateClassPath("templates/controller.java.vm"));
    }


    public static TemplateConfig getTemplateConfig(String name){
        return configMap.get(name);
    }

    public static TemplateConfig register(String name, TemplateConfig config){
        return configMap.put(name, config);
    }


}
