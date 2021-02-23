package pers.tom.generator3.task;

import lombok.Data;
import org.springframework.lang.NonNull;
import pers.tom.generator3.task.data.TemplateRenderData;
import pers.tom.generator3.task.template.TemplateInfo;

/**
 * @author tom
 * @description 模板渲染任务
 *              记录本次渲染的模板 数据 结果
 * @date 2021/2/22 22:44
 */
@Data
public class TemplateRenderTask {

    /**模板信息*/
    private TemplateInfo templateInfo;

    /**渲染数据*/
    private TemplateRenderData renderData;

    /**渲染结果*/
    private Object renderResult;


    public TemplateRenderTask(@NonNull TemplateInfo templateInfo,
                              @NonNull TemplateRenderData renderData){
        this.templateInfo = templateInfo;
        this.renderData = renderData;
    }
}