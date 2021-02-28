package pers.tom.generator3.executor;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;
import pers.tom.generator3.task.template.TemplateInfo;
import pers.tom.generator4.utils.CollectionUtils2;
import pers.tom.generator3.executor.interceptor.TemplateRenderInterceptor;
import pers.tom.generator3.executor.renderer.TemplateRenderer;
import pers.tom.generator3.task.TemplateRenderTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lijia
 * @description 渲染任务的实现类
 * @date 2021-02-23 14:18
 */
@Data
@Accessors(chain = true)
public class TemplateRenderTaskExecutorImpl implements TemplateRenderTaskExecutor{

    /**渲染拦截器*/
    private List<TemplateRenderInterceptor> renderInterceptors;

    /**渲染器*/
    private TemplateRenderer renderer;



    public TemplateRenderTaskExecutorImpl(@NonNull TemplateRenderer renderer){
        this.renderer = renderer;
    }

    @Override
    public boolean execute(TemplateRenderTask renderTask) {

        if(renderTask != null){
            //执行前置渲染
            if(this.applyRreRender(renderTask)){

                TemplateInfo templateInfo = renderTask.getTemplateInfo();
                //判断渲染器是否支持该模板
                if(!renderer.support(templateInfo)){
                    throw new RuntimeException(renderer.getClass().getName() + " 不支持渲染模板 : " + templateInfo.getName());
                }
                //执行渲染
                renderer.render(renderTask);
                //执行后置渲染
                this.applyPostRender(renderTask);
            }

            return renderTask.getRenderResult() != null;
        }

        return false;
    }


    public TemplateRenderTaskExecutorImpl addRenderInterceptor(@NonNull TemplateRenderInterceptor interceptor){

        if(renderInterceptors == null){
            renderInterceptors = new ArrayList<>();
        }
        renderInterceptors.add(interceptor);
        return this;
    }


    /**
     * 执行前置渲染 当其中一个拦截器返回false整体返回false
     * @param renderTask 渲染任务
     * @return 返回值为false将不会执行渲染逻辑
     */
    private boolean applyRreRender(TemplateRenderTask renderTask){

        if(!CollectionUtils2.isEmpty(renderInterceptors)){
            for(TemplateRenderInterceptor interceptor : renderInterceptors){
                if(!interceptor.preRender(renderTask)){

                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 执行后置渲染
     * @param renderTask 渲染任务
     */
    private void applyPostRender(TemplateRenderTask renderTask){

        if(!CollectionUtils2.isEmpty(renderInterceptors)){
            for(TemplateRenderInterceptor interceptor : renderInterceptors){
                interceptor.postRender(renderTask);
            }
        }
    }
}
