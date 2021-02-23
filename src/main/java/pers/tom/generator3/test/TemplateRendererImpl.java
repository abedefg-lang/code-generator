package pers.tom.generator3.test;

import com.sun.istack.internal.NotNull;
import org.springframework.lang.NonNull;
import pers.tom.generator3.executor.renderer.TemplateRenderer;
import pers.tom.generator3.task.TemplateRenderTask;
import pers.tom.generator3.task.template.TemplateInfo;

/**
 * @author lijia
 * @description
 * @date 2021-02-23 15:23
 */
public class TemplateRendererImpl implements TemplateRenderer {

    @Override
    public boolean support(@NonNull TemplateInfo templateInfo) {

        return templateInfo.getName().equals("entity");
    }

    @Override
    public void render(@NotNull TemplateRenderTask renderTask) {
        TemplateInfo templateInfo = renderTask.getTemplateInfo();
        renderTask.setRenderResult("hello world");
        System.out.println(templateInfo);
    }
}
