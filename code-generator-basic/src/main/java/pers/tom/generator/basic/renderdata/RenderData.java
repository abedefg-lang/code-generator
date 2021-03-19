package pers.tom.generator.basic.renderdata;

import java.util.Map;

/**
 * @author lijia
 * @description 渲染数据
 * @date 2021-03-12 13:23
 */
public interface RenderData {

    /**
     * 转换为map格式
     * @return map
     */
    Map<String, Object> toMap();
}
