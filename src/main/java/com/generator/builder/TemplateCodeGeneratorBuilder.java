package com.generator.builder;

import com.generator.CodeGenerator;
import com.generator.TemplateCodeGenerator;

/**
 * TemplateCodeGenerator的Builder
 * 定义了一些子类需要实现的build逻辑
 */
public abstract class TemplateCodeGeneratorBuilder implements CodeGeneratorBuilder{


    /**codeGenerator*/
    protected TemplateCodeGenerator codeGenerator;

    /**
     * build基本的配置信息
     * 包括rootPath parentPackage author...
     */
    protected abstract void buildBasicConfig() throws Exception;

    /**
     * build tableInfos
     * 需要生成的表的信息
     */
    protected abstract void buildTableInfos() throws Exception;

    /**
     * build model的配置信息
     * 是否使用spring mybatisPlus...
     */
    protected abstract void buildModel() throws Exception;

    /**
     * build templates
     * 使用的模板的信息
     */
    protected abstract void buildTemplates() throws Exception;

    /**
     * 解析typeMappings
     */
    protected abstract void parseTypeMappings() throws Exception;


    @Override
    public CodeGenerator build() {
        try {
            parseTypeMappings();
            buildBasicConfig();
            buildTableInfos();
            buildTemplates();
            buildModel();
        }catch (Exception e){
            e.printStackTrace();
        }
        return codeGenerator;
    }
}

