package pers.tom.generator6.template;

import lombok.Getter;
import org.springframework.util.Assert;
import pers.tom.generator6.template.engine.FileTemplateEngine;

import java.io.Writer;
import java.util.Map;

/**
 * @author tom
 * @description 基于文件的模板
 * @date 2021/7/2 23:42
 */
@Getter
public class FileTemplate implements Template{

    /**模板名称*/
    private final String name;

    /**模板文件路径*/
    private final String templatePath;

    /**对应的渲染引擎*/
    private final FileTemplateEngine engine;

    public FileTemplate(String name, String templatePath, FileTemplateEngine engine) {
        this.name = name;
        this.templatePath = templatePath;
        this.engine = engine;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String render(Map<String, Object> data) {
        return engine.execute(templatePath, data);
    }

    @Override
    public void render(Map<String, Object> data, Writer writer) {

        Assert.notNull(writer, "writer不能为null");
        engine.execute(templatePath, data, writer);
    }


}
