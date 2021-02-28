package pers.tom.generator4.template;

import org.springframework.lang.NonNull;
import pers.tom.generator4.template.renderdata.RenderData;

/**
 * @author lijia
 * @description 模板对象
 * @date 2021/2/28 22:47
 */
public interface Template {

    /**默认字符集*/
    String DEFAULT_ENCODING = "UTF-8";

    /**
     * 获取该模板的名称
     * @return name
     */
    @NonNull
    String getName();

    /**
     * 渲染逻辑
     * @param renderData 渲染数据
     * @return 渲染结果
     */
    Object rendering(@NonNull RenderData renderData);
}
