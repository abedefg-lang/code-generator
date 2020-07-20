package com.generator;

import com.tablesource.info.TableInfo;
import com.template.TemplateConfig;
import com.template.render.TemplateRender;
import com.template.render.TemplateRenderRegistry;
import com.utils.NameUtils;
import com.utils.TimeUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 基于模板的方式生成代码
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TemplateCodeGenerator extends AbstractCodeGenerator{

    /**存放需要生成模板*/
    protected Map<String, TemplateConfig> templateConfigMap = new HashMap<>(8);

    /**生成的模型配置*/
    protected ModelConfig model = new ModelConfig();

    @Override
    public void generate() {
        Objects.requireNonNull(tableInfos, "tableInfos不能为null");
        if(!tableInfos.isEmpty() && !templateConfigMap.isEmpty()){
            //将一些基本的信息添加到map
            Map<String, Object> map = putBasicConfig();
            //开始循环生成
            for(TableInfo tableInfo : tableInfos){
                System.out.println("table: "+tableInfo.getTableName());
                map.put("table", tableInfo);
                //每张表都会有自己的一套对应的模板生成的文件名
                map.put("fileNameMap", parseFileName(tableInfo.getClassName()));
                for(TemplateConfig config : templateConfigMap.values()){
                    map.put("template", config);
                    //通过config中的engine属性获取对应的engine
                    TemplateRender engine = TemplateRenderRegistry.getEngine(config.getEngine());
                    if(engine == null){
                        throw new RuntimeException("找不到模板引擎: " + config.getEngine());
                    }
                    //执行渲染逻辑
                    String content = engine.rendering(config.getTemplateClassPath(), map);
                    //写入文件
                    writeCode(content, config.getPath(tableInfo.getClassName()));
                }
            }
        }
    }

    /***
     * 添加模板配置
     * @param config 模板配置
     */
    public void putTemplateConfig(TemplateConfig config){
        templateConfigMap.put(config.getName(), config);
    }


    /**
     * 将一些基本的配置添加到map
     * @return 返回map
     */
    private Map<String, Object> putBasicConfig(){
        Map<String, Object> map = new HashMap<>();
        map.put("packageMap", paresPackage());
        map.put("author", author);
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
        //如果父级包名部位空串 加上"."
        String packagePrefix = "".equals(parentPackage) ? parentPackage : parentPackage + ".";
        Map<String, String> map = new HashMap<>(templateConfigMap.size());
        for(TemplateConfig config : templateConfigMap.values()){
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
        Map<String, String> fileNameMap = new HashMap<>(templateConfigMap.size());
        for(TemplateConfig config : templateConfigMap.values()){
            fileNameMap.put(config.getName(), config.getCompleteTargetFileName(className));
        }
        return fileNameMap;
    }

}
