package pers.tom.generator5.template.filetemplate;

import pers.tom.generator5.template.filetemplate.engine.TemplateEngine;
import pers.tom.generator5.renderdata.java.JavaRenderData;
import pers.tom.generator5.renderdata.RenderData;

/**
 * @author lijia
 * @description
 * @date 2021-03-09 14:22
 */
public class JavaFileTemplate extends FileTemplate {

    public JavaFileTemplate(String templatePath, TemplateEngine engine, String outputRootPath) {
        super(templatePath, engine, outputRootPath);
    }

    @Override
    public boolean support(RenderData renderData) {
        return renderData instanceof JavaRenderData;
    }

    @Override
    protected String getWritePath(RenderData renderData) {
        return null;
    }

}
