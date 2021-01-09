package pers.tom.generator.config;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tom
 * @description 全局配置对象
 * @date 2021/1/4 22:38
 */
@Data
@Accessors(chain = true)
public class GlobalConfig {

    /**是否开启*/
    private boolean open;

    /**代码写入的根路径  默认是项目的根路径*/
    private String outputRootPath;

    /**代码存放的父级包  默认时以com为父级包*/
    private String parentPackage;

    /**当文件已经存在时  是否重写文件*/
    private boolean overwriteFile;

    /**作者*/
    private String author;

    /**是否使用mybatisPlus*/
    private boolean mybatisPlusModel;

    /**是否使用spring*/
    private boolean springModel;

    public GlobalConfig(){

        //初始化设置默认值
        this.open = true;
        this.outputRootPath = System.getProperty("user.dir");
        this.parentPackage = "com";
        this.author = "";
    }

}
