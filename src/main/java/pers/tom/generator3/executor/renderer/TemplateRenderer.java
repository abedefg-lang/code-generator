package pers.tom.generator3.executor.renderer;

import pers.tom.generator3.task.TemplateRenderTask;
import pers.tom.generator3.task.template.TemplateInfo;

/**
 * @author lijia
 * @description 模板渲染器
 * @date 2021-02-23 14:17
 */
public interface TemplateRenderer {

    /**默认字符集*/
    String DEFAULT_ENCODING = "UTF-8";

    /**
     * 判断该渲染器是否支持该模板
     * @param templateInfo 模板信息
     * @return 返回boolean
     */
    boolean support(TemplateInfo templateInfo);

    /**
     * 渲染逻辑 将渲染之后的结果存放到task中
     * @param renderTask 渲染任务
     */
    void render(TemplateRenderTask renderTask);
}
