package pers.tom.generator3.executor.renderer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import pers.tom.generator3.task.TemplateRenderTask;
import pers.tom.generator3.task.data.TemplateRenderData;
import pers.tom.generator3.task.template.TemplateInfo;

import java.io.StringWriter;
import java.util.Map;

/**
 * @author lijia
 * @description velocity渲染器
 * @date 2021-02-23 16:11
 */
public class VelocityRenderer implements TemplateRenderer{

    private final VelocityContext context;

    public VelocityRenderer(){
        this.context = new VelocityContext();
    }

    @Override
    public boolean support(TemplateInfo templateInfo) {
        return true;
    }

    @Override
    public void render(TemplateRenderTask renderTask) {

        this.addProperties(renderTask.getRenderData());

        Template template = Velocity.getTemplate(renderTask.getTemplateInfo().getTemplatePath(), DEFAULT_ENCODING);
        StringWriter writer = new StringWriter();
        template.merge(context, writer);

        renderTask.setRenderResult(writer.toString());
    }

    /**
     * 将数据添加到上下文中
     * @param renderData 渲染数据
     */
    private void addProperties(TemplateRenderData renderData){

        Map<String, Object> map = renderData.toMap();
        for(Map.Entry<String, Object> entry : map.entrySet()){
            context.put(entry.getKey(), entry.getValue());
        }
    }
}
