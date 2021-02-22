package pers.tom.generator2.render.renderer;

import pers.tom.generator2.render.task.TemplateRenderTask;

/**
 * @author lijia
 * @description 模板渲染器
 * @date 2021/2/22 22:49
 */
public interface TemplateRenderer {

    /**
     * 渲染逻辑 将渲染之后的结果存放到task中
     * @param renderTask 渲染任务
     */
    void render(TemplateRenderTask renderTask);

}
