package pers.tom.generator3.executor.handler;

import pers.tom.generator3.task.TemplateRenderTask;

/**
 * @author lijia
 * @description 渲染结果处理器
 * @date 2021-02-23 14:15
 */
public interface TemplateRenderResultHandler {

    /**
     * 处理渲染结果
     * @param renderTask 渲染任务
     */
    void handle(TemplateRenderTask renderTask);
}
