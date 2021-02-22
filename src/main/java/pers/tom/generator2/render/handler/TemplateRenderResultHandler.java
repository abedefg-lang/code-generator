package pers.tom.generator2.render.handler;

/**
 * @author lijia
 * @description 渲染结果处理器
 * @date 2021/2/22 22:54
 */
public interface TemplateRenderResultHandler {

    /**
     * 处理渲染结果
     * @param renderResult 渲染结果
     */
    void handle(Object renderResult);
}
