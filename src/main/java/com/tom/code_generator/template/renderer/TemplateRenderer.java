package com.tom.code_generator.template.renderer;


import com.tom.code_generator.template.TemplateRenderConfig;

/**
 * @author lijia
 * @description 模板渲染器
 * @date 2021/1/9 20:51
 */
public interface TemplateRenderer {

    String DEFAULT_ENCODING = "UTF-8";

    /**
     * 核心渲染方法
     * @param templatePath 模板路径
     * @param config 渲染配置
     * @return 返回渲染结果
     */
    Object render(String templatePath, TemplateRenderConfig config);
}
