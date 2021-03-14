package pers.tom.generator.basic.interceptor;

import pers.tom.generator.basic.renderdata.RenderData;
import pers.tom.generator.basic.template.Template;

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
    default boolean preRendering(Template template, RenderData renderData){
        return true;
    }

    /**
     * 后置渲染
     * @param template 模板
     * @param renderData 渲染数据
     * @param result result
     */
    default void postRendering(Template template, RenderData renderData, Object result){

    }
}
