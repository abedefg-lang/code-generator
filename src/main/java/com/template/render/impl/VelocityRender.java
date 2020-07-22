package com.template.render.impl;

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
    public String rendering(String classpath, Map<String, Object> map) {
        VelocityContext context = new VelocityContext();
        for(Map.Entry<String, Object> entry : map.entrySet()){
            context.put(entry.getKey(), entry.getValue());
        }
        Template template = Velocity.getTemplate(classpath, DEFAULT_ENCODING);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

    @Override
    public String getName() {
        return "velocity";
    }

}
