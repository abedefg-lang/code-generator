package com.template.render;

import com.template.TemplateConfig;

import java.util.Map;

/**
 * 模板渲染器
 */
public interface TemplateRender {

    String DEFAULT_ENCODING = "UTF-8";

    /**
     * 渲染模板
     * @param config 模板配置
     * @param map 参数
     * @return 返回渲染之后的内容
     */
    String rendering(TemplateConfig config, Map<String, Object> map);

    /**
     * 获取模板引擎的名称
     * @return 返回名称
     */
    String getName();
}
