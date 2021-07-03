package pers.tom.generator6.template.engine;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
import java.util.Properties;

/**
 * @author tom
 * @description velocity模板引擎
 * @date 2021/7/2 23:46
 */
public class VelocityEngine implements FileTemplateEngine{

    /**
     * 初始化配置
     * @param properties properties
     */
    public static void init(Properties properties){
        Velocity.init(properties);
    }

    /**
     * 添加配置
     * @param key key
     * @param value value
     */
    public static void addProperty(String key, Object value){
        Velocity.addProperty(key, value);
    }

    /**
     * 清除指定key的配置
     * @param key key
     */
    public static void clearProperty(String key){
        Velocity.clearProperty(key);
    }

    /**
     * 获取配置
     * @param key key
     * @return value
     */
    public static Object getProperty(String key){
        return Velocity.getProperty(key);
    }

    @Override
    public String execute(String templatePath, Map<String, Object> data) {
        StringWriter writer = new StringWriter();
        this.execute(templatePath, data, writer);
        return writer.toString();
    }

    @Override
    public void execute(String templatePath, Map<String, Object> data, Writer writer) {

        Assert.notNull(writer, "writer不能为null");

        VelocityContext vc = this.getContent(data);
        Template template = Velocity.getTemplate(templatePath, DEFAULT_ENCODING);
        template.merge(vc, writer);
    }

    /**
     * 获取上下文
     * @param data 渲染数据
     * @return content
     */
    private VelocityContext getContent(Map<String, Object> data){

        VelocityContext vc = new VelocityContext();
        if(!CollectionUtils.isEmpty(data)){
            data.forEach(vc::put);
        }
        return vc;
    }
}
