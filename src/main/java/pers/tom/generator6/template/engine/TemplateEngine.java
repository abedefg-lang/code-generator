package pers.tom.generator6.template.engine;

import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * @author lijia
 * @description 模板引擎
 * @date 2021-03-09 11:14
 */
public interface TemplateEngine {

    /**默认字符集*/
    String DEFAULT_ENCODING = "UTF-8";

    /**
     *
     * @param templatePath 模板路径
     * @param map map
     * @return 返回执行结果
     */
    String execute(String templatePath, @NonNull Map<String, Object> map);
}
