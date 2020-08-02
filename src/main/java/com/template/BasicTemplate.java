package com.template;

/**
 * 提供的基础模板
 * (entity,dao,service,serviceImpl,controller)
 */
public enum BasicTemplate {

    /**entity*/
    ENTITY(new TemplateConfig()
            .setName("entity")
            .setTargetPackage("entity")
            .setTargetFileName(TemplateConfig.TARGET_NAME_PLACEHOLDER)
            .setEngine("velocity")
            .setTemplateClassPath("templates/entity.java.vm")),

    /**dao*/
    DAO(new TemplateConfig()
            .setName("dao")
            .setTargetPackage("dao")
            .setTargetFileName(TemplateConfig.TARGET_NAME_PLACEHOLDER+"Dao")
            .setEngine("velocity")
            .setTemplateClassPath("templates/dao.java.vm")),

    /**service*/
    SERVICE(new TemplateConfig()
            .setName("service")
            .setTargetPackage("service")
            .setTargetFileName(TemplateConfig.TARGET_NAME_PLACEHOLDER+"Service")
            .setEngine("velocity")
            .setTemplateClassPath("templates/service.java.vm")),

    /**serviceImpl*/
    SERVICE_IMPL(new TemplateConfig()
            .setName("serviceImpl")
            .setTargetPackage("service.impl")
            .setTargetFileName(TemplateConfig.TARGET_NAME_PLACEHOLDER+"ServiceImpl")
            .setEngine("velocity")
            .setTemplateClassPath("templates/serviceImpl.java.vm")),

    /**controller*/
    CONTROLLER(new TemplateConfig()
            .setName("controller")
            .setTargetPackage("controller")
            .setTargetFileName(TemplateConfig.TARGET_NAME_PLACEHOLDER+"Controller")
            .setEngine("velocity")
            .setTemplateClassPath("templates/controller.java.vm"));


    /**基础模板的相关配置*/
    private final TemplateConfig config;

    BasicTemplate(TemplateConfig config){
        this.config = config;
    }

    public TemplateConfig getConfig() {
        return config;
    }
}
