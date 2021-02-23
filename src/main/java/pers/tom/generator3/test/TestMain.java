package pers.tom.generator3.test;

import pers.tom.generator3.executor.TemplateRenderTaskExecutor;
import pers.tom.generator3.executor.TemplateRenderTaskExecutorImpl;
import pers.tom.generator3.executor.handler.WritableRenderResultHandler;
import pers.tom.generator3.executor.renderer.CompositeRenderer;

/**
 * @author lijia
 * @description
 * @date 2021-02-23 15:24
 */
public class TestMain {

    public static void main(String[] args) {

        CompositeRenderer compositeRenderer = new CompositeRenderer();

        TemplateRenderTaskExecutor executor = new TemplateRenderTaskExecutorImpl(compositeRenderer, new WritableRenderResultHandler());

        executor.execute(null);

    }
}
