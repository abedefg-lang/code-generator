package com.tom.code_generator.template;

import com.tom.code_generator.config.GlobalConfig;
import com.tom.code_generator.table.TableInfo;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tom
 * @description 模板渲染的参数  对Map进行包装
 * @date 2021/1/5 23:28
 */
@Data
@Accessors(chain = true)
public class TemplateRenderConfig {

    /**全局配置*/
    private GlobalConfig global;

    /**当前渲染的表*/
    private TableInfo table;

    /**当前渲染的模板*/
    private TemplateInfo template;

    /**已经渲染过的包名  key是对应的模板id*/
    private Map<String, String> packageMap;

    /**已经渲染过的文件名  key是对应的模板id*/
    private Map<String, String> fileNameMap;

    /**参数map存储自定义的参数 */
    private Map<String, Object> paramMap;


    public TemplateRenderConfig(){
        this(null);
    }

    public TemplateRenderConfig(GlobalConfig global){
        this.global = global;
        this.packageMap = new HashMap<>();
        this.fileNameMap = new HashMap<>();
        this.paramMap = new HashMap<>();
    }

    public TemplateRenderConfig addPackage(String templateId, String packageInfo){
        packageMap.put(templateId, packageInfo);
        return this;
    }

    public String getPackage(String templateId){
        return packageMap.get(templateId);
    }

    public String removePackage(String templateId){
        return packageMap.remove(templateId);
    }

    public TemplateRenderConfig addFileName(String templateId, String fileName){
        fileNameMap.put(templateId, fileName);
        return this;
    }

    public String getFileName(String templateId){
        return fileNameMap.get(templateId);
    }

    public String removeFileName(String templateId){
        return fileNameMap.remove(templateId);
    }


    public TemplateRenderConfig addParam(String key, Object value){
        paramMap.put(key, value);
        return this;
    }

    public Object getParam(String key){
        return paramMap.get(key);
    }

    public String removeParam(String key){
        return fileNameMap.remove(key);
    }

    /**
     * 将属性添加到paramMap中  然后返回paramMap
     * @return 返回合并之后的Map
     */
    public Map<String, Object> mergeParam(){
        paramMap.put("global", global);
        paramMap.put("packageMap", packageMap);
        paramMap.put("fileNameMap", fileNameMap);
        paramMap.put("table", table);
        paramMap.put("template", template);
        return this.paramMap;
    }

}
