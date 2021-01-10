package pers.tom.generator.template;


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author lijia
 * @description 通用模板枚举
 * @date 2021/1/10 21:04
 */
public enum GenericTemplate {

    /**entity模板*/
    ENTITY("entity", new TemplateInfo("entity", "src\\main\\resources\\generic-templates\\entity.vm").setTargetFileName(TemplateInfo.RESOURCE_NAME_PLACEHOLDER)),

    /**mapper模板*/
    MAPPER("mapper", new TemplateInfo("mapper", "src\\main\\resources\\generic-templates\\mapper.vm")),

    /**mapperXml模板*/
    MAPPER_XML("mapperXml", new TemplateInfo("mapperXml", "src\\main\\resources\\generic-templates\\mapperXml.vm")),

    /**entity模板*/
    SERVICE("service", new TemplateInfo("service", "src\\main\\resources\\generic-templates\\service.vm")),

    /**mapper模板*/
    SERVICE_IMPL("serviceImpl", new TemplateInfo("serviceImpl", "src\\main\\resources\\generic-templates\\serviceImpl.vm").setTargetPackage("service.impl")),

    /**mapperXml模板*/
    CONTROLLER("controller", new TemplateInfo("controller", "src\\main\\resources\\generic-templates\\controller.vm"));


    /**模板id*/
    private final String templateId;

    /**模板配置信息*/
    private final TemplateInfo templateInfo;

    /**将templateId与templateInfo对应*/
    private static final Map<String, TemplateInfo> TEMPLATE_INFO_MAP = Arrays.stream(GenericTemplate.values()).collect(Collectors.toMap(GenericTemplate::getTemplateId, GenericTemplate::getTemplateInfo));


    GenericTemplate(String templateId, TemplateInfo templateInfo){
        this.templateId = templateId;
        this.templateInfo = templateInfo;
    }

    public static TemplateInfo[] getTemplateInfos(GenericTemplate... genericTemplates){
        int len = genericTemplates.length;
        TemplateInfo[] infos = new TemplateInfo[len];
        for(int i = 0 ; i < len ; i ++){
            infos[i] = genericTemplates[i].templateInfo;
        }
        return infos;
    }


    public static TemplateInfo[] getTemplateInfos(String... templateIds){
        int len = templateIds.length;
        TemplateInfo[] infos = new TemplateInfo[len];
        for(int i = 0 ; i < len ; i ++){
            infos[i] = TEMPLATE_INFO_MAP.get(templateIds[i]);
        }
        return infos;
    }


    public String getTemplateId() {
        return templateId;
    }


    public TemplateInfo getTemplateInfo() {
        return templateInfo;
    }



}
