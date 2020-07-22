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
            .setTemplateClassPath("templates/entity.java.vm")),

    /**dao*/
    DAO(new TemplateConfig()
            .setName("dao")
            .setTargetPackage("dao")
            .setTargetFileName(TemplateConfig.TARGET_NAME_PLACEHOLDER+"Dao")
            .setTemplateClassPath("templates/dao.java.vm")),

    /**service*/
    SERVICE(new TemplateConfig()
            .setName("service")
            .setTargetPackage("service")
            .setTargetFileName(TemplateConfig.TARGET_NAME_PLACEHOLDER+"Service")
            .setTemplateClassPath("templates/service.java.vm")),

    /**serviceImpl*/
    SERVICE_IMPL(new TemplateConfig()
            .setName("serviceImpl")
            .setTargetPackage("service.impl")
            .setTargetFileName(TemplateConfig.TARGET_NAME_PLACEHOLDER+"ServiceImpl")
            .setTemplateClassPath("templates/serviceImpl.java.vm")),

    /**controller*/
    CONTROLLER(new TemplateConfig()
            .setName("controller")
            .setTargetPackage("controller")
            .setTargetFileName(TemplateConfig.TARGET_NAME_PLACEHOLDER+"Controller")
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
