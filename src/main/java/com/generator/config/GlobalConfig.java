package com.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 全局的配置
 */
@Data
@Accessors(chain = true)
public class GlobalConfig {

    /**是否开启*/
    private boolean open = true;

    /**代码写入的根路径  默认是项目的根路径*/
    private String outRootPath = System.getProperty("user.dir");

    /**代码存放的父级包  默认时以com为父级包*/
    private String parentPackage = "com";

    /**当文件已经存在时  是否重写文件*/
    private boolean overwriteFile;

    /**作者*/
    private String author = "";



}
