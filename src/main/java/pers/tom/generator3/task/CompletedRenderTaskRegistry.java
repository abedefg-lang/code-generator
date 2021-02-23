package pers.tom.generator3.task;

import java.util.List;

/**
 * @author tom
 * @description 已完成的渲染任务注册表
 * @date 2021/2/22 23:08
 */
public class CompletedRenderTaskRegistry {

    /**
     * 通过模板名称 数据名称 获取渲染任务
     * @param templateName 模板名称
     * @param renderDataName 渲染名称
     * @return 返回task
     */
    public static TemplateRenderTask getRenderTask(String templateName, String renderDataName){
        return null;
    }

    /**
     * 通过模板获取渲染任务
     * @param templateName 模板名称
     * @return 返回list
     */
    public static List<TemplateRenderTask> getRenderTasks(String templateName){
        return null;
    }

    /**
     * 注册渲染任务
     * @param renderTask task
     */
    public static void register(TemplateRenderTask renderTask){

    }
}
