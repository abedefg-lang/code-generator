package pers.tom.generator3.executor;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;
import pers.tom.generator3.utils.CollectionUtils2;
import pers.tom.generator3.executor.handler.TemplateRenderResultHandler;
import pers.tom.generator3.executor.interceptor.TemplateRenderInterceptor;
import pers.tom.generator3.executor.renderer.TemplateRenderer;
import pers.tom.generator3.task.CompletedRenderTaskRegistry;
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

    /**渲染结果处理器*/
    private TemplateRenderResultHandler renderResultHandler;


    public TemplateRenderTaskExecutorImpl(@NonNull TemplateRenderer renderer,
                                          @NonNull TemplateRenderResultHandler renderResultHandler){


        this.renderer = renderer;
        this.renderResultHandler = renderResultHandler;
    }

    @Override
    public void execute(TemplateRenderTask renderTask) {

        if(renderTask != null){
            //执行前置渲染
            if(this.applyRreRender(renderTask)){

                //判断渲染器是否支持该模板
                if(!renderer.support(renderTask.getTemplateInfo())){
                    throw new RuntimeException(renderer.getClass() + ", 不支持渲染模板 : " + renderTask.getTemplateInfo().getName());
                }
                //执行渲染
                renderer.render(renderTask);
                //执行后置渲染
                this.applyPostRender(renderTask);
                //处理渲染结果
                renderResultHandler.handle(renderTask);
                //注册该任务
                CompletedRenderTaskRegistry.register(renderTask);
            }
        }
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
