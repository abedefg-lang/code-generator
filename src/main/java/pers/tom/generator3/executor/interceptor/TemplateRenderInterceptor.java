package pers.tom.generator3.executor.interceptor;

import pers.tom.generator3.task.TemplateRenderTask;

/**
 * @author tom
 * @description 模板渲染拦截器
 *              该拦截器会在 渲染前，渲染后执行
 * @date 2021/2/22 22:49
 */
public interface TemplateRenderInterceptor {

    /**
     * 前置渲染逻辑  可以通过返回值决定是否执行渲染逻辑
     * @param renderTask 渲染任务
     * @return 如果返回false将不会执行渲染逻辑
     */
    default boolean preRender(TemplateRenderTask renderTask){
        return true;
    }

    /**
     * 后置渲染
     * @param renderTask 渲染任务
     */
    default void postRender(TemplateRenderTask renderTask){

    }
}
