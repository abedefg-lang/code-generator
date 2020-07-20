package com.template;

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
        configMap.put("entity", new TemplateConfig().setName("entity").setTargetPackage("entity").setTargetFileName("$").setTemplateClassPath("templates/entity.java.vm"));
        configMap.put("dao", new TemplateConfig().setName("dao").setTargetPackage("dao").setTargetFileName("$Dao").setTemplateClassPath("templates/dao.java.vm"));
        configMap.put("service", new TemplateConfig().setName("service").setTargetPackage("service").setTargetFileName("$Service").setTemplateClassPath("templates/service.java.vm"));
        configMap.put("serviceImpl", new TemplateConfig().setName("serviceImpl").setTargetPackage("service.impl").setTargetFileName("$ServiceImpl").setTemplateClassPath("templates/serviceImpl.java.vm"));
        configMap.put("controller", new TemplateConfig().setName("controller").setTargetPackage("controller").setTargetFileName("$Controller").setTemplateClassPath("templates/controller.java.vm"));
    }


    public static TemplateConfig getTemplateConfig(String name){
        return configMap.get(name);
    }

    public static TemplateConfig register(String name, TemplateConfig config){
        return configMap.put(name, config);
    }


}
