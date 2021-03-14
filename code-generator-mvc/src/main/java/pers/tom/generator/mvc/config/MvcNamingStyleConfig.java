package pers.tom.generator.mvc.config;

import lombok.Data;

/**
 * @author tom
 * @description 命名风格配置
 * @date 2021/3/14 23:14
 */
@Data
public class MvcNamingStyleConfig {

    /**类名占位符 (比如 I%sService 处理后为 IUserService)*/
    public static final String CLASS_NAME_PLACE_HOLDER = "%s";

    /**entity命名风格*/
    private String entityNamingStyle;

    /**mapper命名风格*/
    private String mapperNamingStyle;

    /**service接口命名风格*/
    private String serviceNamingStyle;

    /**service实现类命名风格*/
    private String serviceImplNamingStyle;

    /**controller命名风格*/
    private String controllerNamingStyle;


    public String getEntityName(String className){
        return getRealClassName(entityNamingStyle, className);
    }

    public String getMapperName(String className){
        return getRealClassName(mapperNamingStyle, className);
    }

    public String getServiceName(String className){
        return getRealClassName(serviceNamingStyle, className);
    }

    public String getServiceImplName(String className){
        return getRealClassName(serviceImplNamingStyle, className);
    }

    public String getControllerName(String className){
        return getRealClassName(controllerNamingStyle, className);
    }

    /**
     * 获取处理之后的真正类名
     * @param namingStyle 命名风格
     * @param className 类名
     * @return 返回处理之后的类名
     */
    public static String getRealClassName(String namingStyle, String className){

        return String.format(namingStyle, className);
    }

    /**
     * 获取默认命名风格配置
     * @return 返回config
     */
    public static MvcNamingStyleConfig getDefaultConfig(){

        MvcNamingStyleConfig namingStyleConfig = new MvcNamingStyleConfig();

        //设置默认配置
        namingStyleConfig.setEntityNamingStyle(CLASS_NAME_PLACE_HOLDER);
        namingStyleConfig.setMapperNamingStyle(CLASS_NAME_PLACE_HOLDER+"Mapper");
        namingStyleConfig.setServiceNamingStyle(CLASS_NAME_PLACE_HOLDER+"Service");
        namingStyleConfig.setServiceImplNamingStyle(CLASS_NAME_PLACE_HOLDER+"ServiceImpl");
        namingStyleConfig.setControllerNamingStyle(CLASS_NAME_PLACE_HOLDER+"Controller");

        return namingStyleConfig;
    }
}
