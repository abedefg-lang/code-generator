package pers.tom.generator.basic.template;

import org.springframework.lang.NonNull;
import pers.tom.generator.basic.exception.RenderException;

import java.util.Map;

/**
 * @author lijia
 * @description 模板
 * @date 2021-03-12 13:09
 */
public interface Template {


    /**
     * 获取模板名称
     * @return name
     */
    String getName();

    /**
     * 渲染逻辑
     * @param renderParam 渲染参数
     * @return 渲染结果
     * @throws RenderException 渲染异常
     */
    Object rendering(@NonNull Map<String, Object> renderParam) throws RenderException;
}
