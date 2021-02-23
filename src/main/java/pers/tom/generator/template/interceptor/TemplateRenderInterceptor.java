package pers.tom.generator.template.interceptor;

import pers.tom.generator.template.TemplateRenderContext;

/**
 * @author tom
 * @description 模板渲染拦截器  在渲染之前后渲染之后都会执行该拦截器
 * @date 2021/1/8 21:28
 */
public interface TemplateRenderInterceptor {

    /**
     * 渲染前置处理
     * @param context 渲染上下文
     * @return 返回是否执行渲染
     */
    default boolean preRender(TemplateRenderContext context){
        return true;
    }

    /**
     * 渲染后置处理
     * @param renderResult 渲染结果
     * @param context 渲染上下文
     */
    default void postRender(Object renderResult, TemplateRenderContext context){

    }

}
