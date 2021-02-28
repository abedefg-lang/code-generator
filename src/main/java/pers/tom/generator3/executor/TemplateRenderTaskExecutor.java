package pers.tom.generator3.executor;

import pers.tom.generator3.task.TemplateRenderTask;

/**
 * @author lijia
 * @description 模板渲染任务执行器
 * @date 2021-02-23 14:13
 */
public interface TemplateRenderTaskExecutor {

    /**
     * 核心执行方法
     * @param renderTask 渲染任务
     */
    boolean execute(TemplateRenderTask renderTask);
}
