package pers.tom.generator.basic.template.engine;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;


import java.io.StringWriter;
import java.util.Map;
import java.util.Properties;

/**
 * @author lijia
 * @description velocity 模板引擎
 * @date 2021-03-09 11:31
 */
public class VelocityTemplateEngine implements TemplateEngine{


    private final VelocityContext context;

    public VelocityTemplateEngine(){
        this(null);
    }

    public VelocityTemplateEngine(Properties properties){
        if(properties != null){
            Velocity.init(properties);
        }
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
