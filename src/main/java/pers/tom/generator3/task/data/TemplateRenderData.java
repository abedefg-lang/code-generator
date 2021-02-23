package pers.tom.generator3.task.data;

import java.util.Map;

/**
 * @author lijia
 * @description 模板渲染数据
 * @date 2021/2/22 22:39
 */
public interface TemplateRenderData {

    /**
     * 获取名称 该名称用来标识，寻找该数据该数据
     * @return 返回名称
     */
    String getName();

    /**
     * 转换成map
     * @return 返回map
     */
    Map<String, Object> toMap();
}
