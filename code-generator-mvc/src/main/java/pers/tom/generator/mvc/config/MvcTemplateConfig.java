package pers.tom.generator.mvc.config;

import lombok.Data;
import pers.tom.generator.basic.template.FileTemplate;
import pers.tom.generator.basic.template.engine.VelocityEngine;

/**
 * @author tom
 * @description mvc模板配置
 * @date 2021/3/14 11:23
 */
@Data
public class MvcTemplateConfig {

    /**entity模板*/
    private FileTemplate entityTemplate;

    /**mapper模板*/
    private FileTemplate mapperTemplate;

    /**service接口模板*/
    private FileTemplate serviceTemplate;

    /**service实现类模板*/
    private FileTemplate serviceImplTemplate;

    /**controller模板*/
    private FileTemplate controllerTemplate;

    /**
     * 获取默认的模板配置
     * @return config
     */
    public static MvcTemplateConfig getDefaultConfig(){

        MvcTemplateConfig config = new MvcTemplateConfig();
        VelocityEngine engine = new VelocityEngine();
        config.setEntityTemplate(new FileTemplate("entity", "", engine));

        return config;
    }

    /**
     * 获取mybatisPlus风格的模板配置
     * @return config
     */
    public static MvcTemplateConfig getMybatisPlusStyleConfig(){

        MvcTemplateConfig config = new MvcTemplateConfig();
        VelocityEngine engine = new VelocityEngine();
        config.setEntityTemplate(new FileTemplate("entity", "", engine));
        return config;
    }
}
