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


    /**
     * 获取默认的全局配置
     * @return 返回全局配置
     */
    public static GlobalConfig getDefaultGlobalConfig(){
        GlobalConfig global = new GlobalConfig();

        global.setOpen(true);
        //默认输出路径为项目根路径
        global.setOutputRootPath(System.getProperty("user.dir"));
        //默认包名为com
        global.setParentPackage("com");
        global.setAuthor("");

        return global;
    }

}
