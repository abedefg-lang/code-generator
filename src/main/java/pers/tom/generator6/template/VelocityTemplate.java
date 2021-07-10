package pers.tom.generator6.template;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.Writer;
import java.util.Map;

/**
 * @author tom
 * @description velocity模板
 * @date 2021/7/10 12:37
 */
public class VelocityTemplate extends AbstractFileTemplate {

    /**velocity包下的模板*/
    private final org.apache.velocity.Template template;

    public VelocityTemplate(String name, String templatePath) {
        super(name, templatePath);
        this.template = Velocity.getTemplate(templatePath, DEFAULT_ENCODING);
    }

    @Override
    public void render(Map<String, Object> param, Writer writer) {

        Assert.notNull(writer, "writer不能为null");

        VelocityContext vc = this.getContent(param);
        template.merge(vc, writer);
    }

    /**
     * 获取上下文
     * @param param 渲染参数
     * @return context
     */
    private VelocityContext getContent(Map<String, Object> param){

        VelocityContext vc = new VelocityContext();
        if(!CollectionUtils.isEmpty(param)){
            param.forEach(vc::put);
        }
        return vc;
    }

}
