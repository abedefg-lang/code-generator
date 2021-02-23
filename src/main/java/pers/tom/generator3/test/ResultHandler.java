package pers.tom.generator3.test;

import pers.tom.generator3.executor.handler.TemplateRenderResultHandler;
import pers.tom.generator3.task.TemplateRenderTask;

/**
 * @author lijia
 * @description
 * @date 2021-02-23 15:23
 */
public class ResultHandler implements TemplateRenderResultHandler {

    @Override
    public void handle(TemplateRenderTask renderTask) {
        System.out.println(renderTask.getRenderResult());
    }
}
