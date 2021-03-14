package pers.tom.generator.mvc.config;

import lombok.Data;

/**
 * @author tom
 * @description 包配置
 * @date 2021/3/14 23:11
 */
@Data
public class MvcPackageConfig {

    /**entity包名*/
    private String entityPackage;

    /**mapper包名*/
    private String mapperPackage;

    /**service接口包名*/
    private String servicePackage;

    /**service实现类包名*/
    private String serviceImplPackage;

    /**controller包名*/
    private String controllerPackage;

    /**
     * 获取默认配置对象
     * @return package config
     */
    public static MvcPackageConfig getDefaultConfig(){

        MvcPackageConfig packageConfig = new MvcPackageConfig();

        //设置默认包名
        packageConfig.setEntityPackage("com.entity");
        packageConfig.setMapperPackage("com.mapper");
        packageConfig.setServicePackage("com.service");
        packageConfig.setServiceImplPackage("com.service.impl");
        packageConfig.setControllerPackage("com.controller");

        return packageConfig;
    }
}
