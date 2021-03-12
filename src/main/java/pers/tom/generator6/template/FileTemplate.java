package pers.tom.generator6.template;

import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import pers.tom.generator6.exception.RenderException;
import pers.tom.generator6.template.engine.TemplateEngine;

import java.util.Map;

/**
 * @author lijia
 * @description 文件形式的模板  渲染结果为字符串
 * @date 2021-03-12 13:13
 */
@Data
public class FileTemplate implements Template{

    /**模板名称*/
    private final String name;

    /**模板文件路径*/
    private String templateFilePath;

    /**模板引擎*/
    private TemplateEngine engine;

    public FileTemplate(@NonNull String name){
        this(name, null, null);
    }

    public FileTemplate(@NonNull String name, String templateFilePath, TemplateEngine engine){
        this.name = name;
        this.templateFilePath = templateFilePath;
        this.engine = engine;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String rendering(Map<String, Object> renderParam) throws RenderException {

        Assert.notNull(templateFilePath, "templateFilePath不能为null");
        Assert.notNull(engine, "engine不能为null");

        return engine.execute(templateFilePath, renderParam);
    }
}
