package pers.tom.generator5.java.template;


import pers.tom.generator5.java.renderdata.JavaFileRenderData;
import pers.tom.generator5.template.FileTemplate;
import pers.tom.generator.basic.template.engine.TemplateEngine;

import java.io.File;

/**
 * @author lijia
 * @description
 * @date 2021-03-09 14:22
 */
public class JavaFileTemplate<DATA extends JavaFileRenderData> extends FileTemplate<DATA> {

    public JavaFileTemplate(String templatePath, TemplateEngine engine){

        super(templatePath, engine);
    }

    public JavaFileTemplate(String templatePath, TemplateEngine engine, String outputRootPath){

        super(templatePath, engine, outputRootPath);
    }

    @Override
    protected String getWritePath(JavaFileRenderData renderData) {

        String separator = File.separator;
        return outputRootPath + separator + renderData.getPackageName().replace(".", separator) + separator + renderData.getClassName() + ".java";
    }

}
