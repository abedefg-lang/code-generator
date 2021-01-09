package com.tom.code_generator.template.interceptor;

import com.tom.code_generator.template.TemplateRenderConfig;

/**
 * @author tom
 * @description 模板渲染拦截器  在渲染之前后渲染之后都会执行该拦截器
 * @date 2021/1/8 21:28
 */
public interface TemplateRenderInterceptor {

    /**
     * 渲染前置处理
     * @param config 渲染配置
     * @return 返回是否执行渲染
     */
    default boolean preRender(TemplateRenderConfig config){
        return true;
    }

    /**
     * 渲染后置处理
     * @param renderResult 渲染结果
     * @param param 渲染参数
     */
    default void postRender(Object renderResult, TemplateRenderConfig param){

    }

}
