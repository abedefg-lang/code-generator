package com.template.render.impl;

import com.template.TemplateConfig;
import com.template.render.TemplateRender;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Map;

/**
 * 通过velocity模板引擎渲染
 */
public class VelocityRender implements TemplateRender {

    static {
        Velocity.addProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        Velocity.addProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();
    }

    @Override
    public String render(TemplateConfig config, Map<String, Object> map) {
        //创建context
        VelocityContext context = new VelocityContext(map);
        context.put("template", config);
        //获取模板
        Template template = Velocity.getTemplate(config.getTemplateClassPath(), DEFAULT_ENCODING);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        //返回渲染之后的内容
        return writer.toString();
    }

    @Override
    public String getTemplateEngine() {
        return "velocity";
    }

}
