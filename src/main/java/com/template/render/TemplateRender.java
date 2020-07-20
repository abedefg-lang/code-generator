package com.template.render;

import java.util.Map;

/**
 * 模板渲染器
 */
public interface TemplateRender {


    /**
     * 渲染模板
     * @param classpath 模板的classpath路径
     * @param map 一些参数
     * @return 返回渲染之后的内容
     */
    String rendering(String classpath, Map<String, Object> map);
}
