package com.generator;

import com.config.ModelConfig;
import com.config.TemplateConfig;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 使用模板的方式生成代码
 */
@Data
public abstract class TemplateCodeGenerator extends AbstractCodeGenerator{

    /**存放需要生成模板*/
    protected Map<String, TemplateConfig> templateConfigMap = new HashMap<>(8);

    /**生成的模型配置*/
    protected ModelConfig model = new ModelConfig();

    /***
     * 添加模板配置
     * @param config 模板配置
     */
    public void putTemplateConfig(TemplateConfig config){
        templateConfigMap.put(config.getName(), config);
    }


    /**
     * 获取代码写入的路径
     * @param tableName 表名
     * @param config 模板配置
     * @return 返回路径
     */
    protected String getWritePath(String tableName, TemplateConfig config){
        //获取模板生成的文件格式
        //从第一个. 到倒数第三个字符(也就是除去.vm)
        String templatePath = config.getTemplateClassPath();
        String fileFormat = templatePath.substring(templatePath.indexOf("."), templatePath.length()-3);
        String className = config.getFileName().replace("$", tableName)+fileFormat;
        return outRootPath+"\\"+(parentPackage+"\\"+config.getPackageName()).replace(".", "\\")+"\\"+className;
    }
}
