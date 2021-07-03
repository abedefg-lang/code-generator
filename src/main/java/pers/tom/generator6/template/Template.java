package pers.tom.generator6.template;

import org.springframework.lang.NonNull;

import java.io.Writer;
import java.util.Map;

/**
 * @author lijia
 * @description 模板
 * @date 2021/6/12 22:58
 */
public interface Template {

    /**
     * 获取当前模板的名称
     * @return template
     */
    String getName();

    /**
     * 渲染数据 返回结果
     * @param param 渲染参数
     * @return 渲染结果
     */
    Object render(Map<String, Object> param);

    /**
     * 渲染数据并将结果输出
     * @param param 渲染参数
     * @param writer writer
     */
    void render(Map<String, Object> param, @NonNull Writer writer);
}
