package pers.tom.generator.template.renderer;

import pers.tom.generator.template.TemplateRenderContext;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;

import java.io.StringWriter;
import java.util.Map;

/**
 * @author tom
 * @description velocity模板渲染器
 * @date 2021/1/9 21:52
 */
public class VelocityRenderer implements TemplateRenderer {

    private final VelocityContext context;

    public VelocityRenderer(){
        this.context = new VelocityContext();
    }

    @Override
    public String render(String templatePath, TemplateRenderContext config) {

        this.addProperties(config);
        Template template = Velocity.getTemplate(templatePath, DEFAULT_ENCODING);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        return writer.toString();
    }

    private void addProperties(TemplateRenderContext renderConfig){
        Map<String, Object> paramMap = renderConfig.mergeParam();
        for(Map.Entry<String, Object> entry : paramMap.entrySet()){
            context.put(entry.getKey(), entry.getValue());
        }
    }
}
