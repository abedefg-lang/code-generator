package pers.tom.generator.mvc.config;

import lombok.Data;
import pers.tom.generator.basic.template.FileTemplate;
import pers.tom.generator.basic.template.engine.VelocityTemplateEngine;

import java.util.Properties;


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
     * 获取mybatisPlus风格的模板配置
     * @return config
     */
    public static MvcTemplateConfig getMybatisPlusStyleConfig(){

        MvcTemplateConfig config = new MvcTemplateConfig();

        //创建模板引擎
        Properties properties = new Properties();
        properties.setProperty("file.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityTemplateEngine engine = new VelocityTemplateEngine(properties);

        //设置模板
        config.setEntityTemplate(new FileTemplate("entity", "template/mybatis-plus/entity.vm", engine));
        config.setMapperTemplate(new FileTemplate("mapper", "template/mybatis-plus/mapper.vm", engine));
        config.setServiceTemplate(new FileTemplate("service", "template/mybatis-plus/service.vm", engine));
        config.setServiceImplTemplate(new FileTemplate("serviceImpl", "template/mybatis-plus/serviceImpl.vm", engine));
        config.setControllerTemplate(new FileTemplate("controller", "template/mybatis-plus/controller.vm", engine));

        return config;
    }
}
