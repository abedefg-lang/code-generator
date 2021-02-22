package pers.tom.generator2.render.task;

import lombok.Data;
import pers.tom.generator2.render.data.TemplateRenderData;
import pers.tom.generator2.template.TemplateInfo;

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
}
