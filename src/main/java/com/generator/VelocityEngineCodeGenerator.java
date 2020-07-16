package com.generator;

import com.tablesource.info.TableInfo;
import com.config.TemplateConfig;
import com.utils.NameUtils;
import com.utils.TimeUtil;
import lombok.Data;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.StringWriter;
import java.util.Map;

/**
 * 使用velocity模板生成代码
 */
@Data
public class VelocityEngineCodeGenerator extends TemplateCodeGenerator{

    /**context*/
    private VelocityContext context = new VelocityContext();

    @Override
    public void generate() {
        //当tableInfos有数据 并且添加了模板 才执行生成逻辑
        if(tableInfos != null && !tableInfos.isEmpty() && !templateConfigMap.isEmpty()) {
            //put配置
            this.putConfig();
            System.out.println("-------------开始生成----------------");
            long startTime = System.currentTimeMillis();
            //循环生成
            for(Map.Entry<String, TemplateConfig> entry : templateConfigMap.entrySet()) {
                TemplateConfig config = entry.getValue();
                System.out.println("template: "+config.getName());
                Template template = Velocity.getTemplate(config.getTemplateClassPath(), "UTF-8");
                context.put("config", config);
                for(TableInfo tableInfo : tableInfos) {
                    context.put("table", tableInfo);
                    StringWriter writer = new StringWriter();
                    template.merge(context, writer);
                    writeCode(writer.toString(), getWritePath(tableInfo.getClassName(), config));
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("-------生成完毕 耗时: "+(endTime-startTime)+"毫秒----------");
        }
    }

    /**
     * 将通用的配置put进context中
     */
    private void putConfig(){
        context.put("parentPackage", parentPackage);
        context.put("configMap", templateConfigMap);
        context.put("NameUtils", NameUtils.class);
        context.put("model", model);
        context.put("author", author);
        context.put("date", TimeUtil.getCurrentTime());
    }


    static {
        Velocity.addProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        Velocity.addProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        Velocity.init();
    }
}
