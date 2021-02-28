package pers.tom.generator4.template;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import pers.tom.generator4.template.renderdata.RenderData;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author tom
 * @description velocity模板对象
 * @date 2021/2/28 23:02
 */
public class VelocityTemplate extends FileFormatTemplate {

    /**velocity context*/
    private final VelocityContext context;

    public VelocityTemplate(String name, String templatePath) {

        super(name, templatePath);
        this.context = new VelocityContext();
    }

    @Override
    public Object rendering(RenderData renderData) {

        this.putData(renderData);

        Template template = Velocity.getTemplate(getTemplatePath(), DEFAULT_ENCODING);
        try(StringWriter writer = new StringWriter()){
            template.merge(context, writer);
            return writer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将数据添加到上下文中
     * @param renderData 渲染数据
     */
    private void putData(RenderData renderData){

        Map<String, Object> map = renderData.toMap();
        for(Map.Entry<String, Object> entry : map.entrySet()){
            context.put(entry.getKey(), entry.getValue());
        }
    }
}
