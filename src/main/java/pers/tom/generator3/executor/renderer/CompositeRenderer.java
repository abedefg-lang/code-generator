package pers.tom.generator3.executor.renderer;

import org.springframework.lang.NonNull;
import pers.tom.generator3.task.TemplateRenderTask;
import pers.tom.generator3.task.template.TemplateInfo;
import pers.tom.generator4.utils.CollectionUtils2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijia
 * @description 复合渲染器
 * @date 2021-02-23 16:37
 */
public class CompositeRenderer implements TemplateRenderer{

    /**子渲染器集合*/
    private List<TemplateRenderer> subRenderers;

    @Override
    public boolean support(TemplateInfo templateInfo) {

        return this.findSubRenderer(templateInfo) != null;
    }

    @Override
    public void render(TemplateRenderTask renderTask) {

        TemplateRenderer subRenderer = this.findSubRenderer(renderTask.getTemplateInfo());
        if(subRenderer != null){
            subRenderer.render(renderTask);
        }
    }

    /**
     * 添加子渲染器
     * @param subRenderer subRenderer
     * @return 返回自身
     */
    public CompositeRenderer addSubRenderer(@NonNull TemplateRenderer subRenderer){

        if(subRenderers == null){
            subRenderers = new ArrayList<>();
        }
        subRenderers.add(subRenderer);
        return this;
    }

    /**
     * 找到支持指定模板的子渲染器
     * @param templateInfo 模板信息
     * @return 返回子渲染器
     */
    protected TemplateRenderer findSubRenderer(TemplateInfo templateInfo){

        if(!CollectionUtils2.isEmpty(subRenderers)){
            for(TemplateRenderer subRenderer : subRenderers){
                if(subRenderer.support(templateInfo)){
                    return subRenderer;
                }
            }
        }
        return null;
    }
}
