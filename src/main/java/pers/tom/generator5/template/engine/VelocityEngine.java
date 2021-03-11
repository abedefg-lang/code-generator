package pers.tom.generator5.template.engine;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.Map;

/**
 * @author lijia
 * @description velocity 引擎
 * @date 2021-03-09 11:31
 */
public class VelocityEngine implements TemplateEngine{

    private final VelocityContext context;

    public VelocityEngine(){
        this.context = new VelocityContext();
    }

    @Override
    public String execute(String templatePath, Map<String, Object> map) {

        map.keySet().forEach(s -> context.put(s, map.get(s)));

        Template template = Velocity.getTemplate(templatePath, DEFAULT_ENCODING);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }
}
