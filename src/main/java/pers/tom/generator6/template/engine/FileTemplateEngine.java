package pers.tom.generator6.template.engine;

import java.io.Writer;
import java.util.Map;

/**
 * @author lijia
 * @description 模板引擎
 * @date 2021/7/2 23:42
 */
public interface FileTemplateEngine {

    /**默认字符集*/
    String DEFAULT_ENCODING = "UTF-8";

    /**
     * 模板引擎执行逻辑
     * @param templatePath 模板路径
     * @param data 渲染数据
     * @return 渲染结果
     */
    String execute(String templatePath, Map<String, Object> data);

    /**
     * 模板引擎执行逻辑 并将结果写入
     * @param templatePath 模板路径
     * @param data 渲染数据
     * @param writer writer
     */
    void execute(String templatePath, Map<String, Object> data, Writer writer);
}
