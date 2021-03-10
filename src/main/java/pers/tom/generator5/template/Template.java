package pers.tom.generator5.template;

import pers.tom.generator5.renderdata.RenderData;
import pers.tom.generator5.renderresult.RenderResult;

/**
 * @author lijia
 * @description 模板
 * @date 2021-03-09 11:08
 */
public interface Template {

    /**
     * 判断该模板是否支持这种格式的渲染数据
     * @param renderData 渲染数据
     * @return 返回boolean
     */
    boolean support(RenderData renderData);

    /**
     * 模板渲染逻辑
     * 渲染前会执行support方法  如果不支持会抛出异常
     * @param renderData 渲染数据
     * @return 渲染结果
     */
    RenderResult rendering(RenderData renderData);
}
