package com.template;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * 模板的配置
 */
@Data
@Accessors(chain = true)
public class TemplateConfig {

    /**默认的生成的文件格式*/
    private static final String DEFAULT_TARGET_FILE_FORMAT = "java";

    /**占位符*/
    private static final String TARGET_NAME_PLACEHOLDER = "$";

    /**模板名称 */
    private String name;

    /**生成的代码存放的包*/
    private String targetPackage;

    /**生成的文件名  但是可能只知道一部分  不确定的部分*/
    private String targetFileName;

    /**
     * 模板的路径  路径规定为 name.生成的文件格式.该文件的格式
     * 就是说在普通文件的后缀前多了一个生成的文件格式
     */
    private String templateClassPath;

    /**这个模板使用的引擎  默认是velocity*/
    private String engine = "velocity";

    /**如果使用自己写的模板  但是想传递一些配置  可以添加到这个Map中*/
    private Map<String, String> propertyMap;


    public TemplateConfig putProperty(String key, String value){
        if (propertyMap == null) {
            propertyMap = new HashMap<>(8);
        }
        propertyMap.put(key, value);
        return this;
    }


    /**
     * 获取生成文件的后缀格式
     * @return 返回String
     */
    public String getTargetFileFormat(){
        //获取第一个"." 与最后一个"."  的索引位置
        int firstIndex = templateClassPath.indexOf(".");
        int lastIndex = templateClassPath.lastIndexOf(".");
        //如果两个index相等  说明没有配置中间的格式  返回默认格式
        return firstIndex == lastIndex ? DEFAULT_TARGET_FILE_FORMAT : templateClassPath.substring(firstIndex+1, lastIndex);
    }

    /**
     * 获取完整的文件名 (将占位符替换成targetName)
     * @param targetName targetName
     * @return 返回字符串
     */
    public String getCompleteTargetFileName(String targetName){
        return targetFileName.replace(TARGET_NAME_PLACEHOLDER, targetName);
    }

    /**
     * 获取路径
     * @param targetName targetName
     * @return 返回路径
     */
    public String getPath(String targetName){
        //包名+文件完整名+文件后缀
        return targetPackage.replace(".", "\\")+"\\"+getCompleteTargetFileName(targetName)+"."+getTargetFileFormat();
    }


}
