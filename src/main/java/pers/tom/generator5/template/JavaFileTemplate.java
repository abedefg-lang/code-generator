package pers.tom.generator5.template;

import pers.tom.generator5.renderdata.JavaRenderData;
import pers.tom.generator5.renderdata.RenderData;
import pers.tom.generator5.template.engine.TemplateEngine;

/**
 * @author lijia
 * @description
 * @date 2021-03-09 14:22
 */
public class JavaFileTemplate extends FileTemplate {

    public JavaFileTemplate(String templatePath, TemplateEngine engine) {
        super(templatePath, engine);
    }

    @Override
    public boolean support(RenderData renderData) {
        return renderData instanceof JavaRenderData;
    }

}
