package pers.tom.generator4.template.renderdata;

import org.springframework.lang.NonNull;

import java.util.Map;

/**
 * @author tom
 * @description 渲染数据对象
 * @date 2021/2/28 22:49
 */
public interface RenderData {

    /**
     * 转换为map
     * @return 返回map
     */
    @NonNull
    Map<String, Object> toMap();
}
