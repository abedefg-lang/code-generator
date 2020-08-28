package com.generator;

import com.generator.config.GlobalConfig;
import com.generator.config.ModelConfig;
import com.tablesource.info.TableInfo;
import com.template.TemplateConfig;
import com.template.render.TemplateRender;
import com.template.render.TemplateRenderRegistry;
import com.utils.NameUtils;
import com.utils.TimeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.*;

/**
 * 基于模板的方式生成代码
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateCodeGenerator extends AbstractCodeGenerator{

    /**模板*/
    private List<TemplateConfig> templateConfigList = new ArrayList<>(8);

    /**生成的模型配置*/
    private ModelConfig model;

    @Override
    public void generate(){
        //判断是否开启生成器
        if(global.isOpen()){
            validate();
            //将一些基本的信息添加到map
            Map<String, Object> map = putBasicConfig();
            //开始循环生成
            TemplateRender render;
            for(TableInfo tableInfo : tableInfos){
                System.out.println("开始生成 table: " + tableInfo.getTableName());
                map.put("table", tableInfo);
                //每张表都会有自己的一套对应的模板生成的文件名
                map.put("fileNameMap", parseFileName(tableInfo.getClassName()));
                for(TemplateConfig config : templateConfigList){
                    map.put("template", config);
                    //通过config中的engine属性获取对应的render
                    render = TemplateRenderRegistry.getRender(config.getEngine());
                    if(render == null){
                        throw new RuntimeException("找不到模板渲染器: engine " + config.getEngine() + " ,template : " + config.getName());
                    }
                    //执行渲染逻辑  获取渲染之后的字符串
                    String content = render.render(config, map);
                    //写入文件
                    writeCode(content, getParentWritePath()+"\\"+config.getPath(tableInfo.getClassName()));
                }
            }
        }
    }

    /**
     * 添加模板配置
     * @param config 模板配置
     */
    public void putTemplateConfig(TemplateConfig config){
        templateConfigList.add(config);
    }

    /**
     * 验证 这个方法会在执行生成方法之前执行
     * 判断一些属性
     */
    protected void validate(){
        if(tableInfos == null){
            throw new RuntimeException("tableInfos不能为null");
        }
        if(global == null){
            global = new GlobalConfig();
        }
        if(model == null){
            model = new ModelConfig();
        }
    }

    /**
     * 将一些基本的配置添加到map
     * @return 返回map
     */
    private Map<String, Object> putBasicConfig(){
        Map<String, Object> map = new HashMap<>();
        map.put("packageMap", paresPackage());
        map.put("author", global.getAuthor());
        map.put("model", model);
        map.put("date", TimeUtil.getCurrentTime());
        map.put("NameUtils", NameUtils.class);
        return map;
    }

    /**
     * 将所有的模板中的package信息 解析成一个Map
     * @return 返回Map
     */
    private Map<String, String> paresPackage(){
        String parentPackage = global.getParentPackage();
        //如果父级包名部位空串 加上"."
        String packagePrefix = "".equals(parentPackage) ? parentPackage : parentPackage + ".";
        Map<String, String> map = new HashMap<>();
        for(TemplateConfig config : templateConfigList){
            map.put(config.getName(), packagePrefix+config.getTargetPackage());
        }
        return map;
    }

    /**
     * 将一个模板对应的文件名存放在一起
     * @param className 类名
     * @return 返回Map
     */
    private Map<String, String> parseFileName(String className){
        Map<String, String> fileNameMap = new HashMap<>();
        for(TemplateConfig config : templateConfigList){
            fileNameMap.put(config.getName(), config.getCompleteTargetFileName(className));
        }
        return fileNameMap;
    }

}
