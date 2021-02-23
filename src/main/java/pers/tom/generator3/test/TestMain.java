package pers.tom.generator3.test;

import pers.tom.generator3.executor.TemplateRenderTaskExecutor;
import pers.tom.generator3.executor.TemplateRenderTaskExecutorImpl;
import pers.tom.generator3.task.TemplateRenderTask;
import pers.tom.generator3.task.template.TemplateInfo;

/**
 * @author lijia
 * @description
 * @date 2021-02-23 15:24
 */
public class TestMain {

    public static void main(String[] args) {
        TemplateRenderTaskExecutor executor = new TemplateRenderTaskExecutorImpl(new TemplateRendererImpl(), new ResultHandler());
        TemplateRenderTask renderTask = new TemplateRenderTask(new TemplateInfo().setName("entity"), null);

        executor.execute(renderTask);
    }
}
