package pers.tom.generator6.interceptor;

import pers.tom.generator6.renderdata.RenderData;
import pers.tom.generator6.template.Template;

/**
 * @author lijia
 * @description 模板渲染拦截器
 * @date 2021-03-12 17:28
 */
public interface TemplateRenderInterceptor {

    /**
     * 前置渲染
     * @param template 模板
     * @param renderData 渲染数据
     * @return 是否可以执行渲染
     */
    boolean preRendering(Template template, RenderData renderData);

    /**
     * 后置渲染
     * @param template 模板
     * @param renderData 渲染数据
     * @param result result
     */
    default void postRendering(Template template, RenderData renderData, Object result){

    }
}
