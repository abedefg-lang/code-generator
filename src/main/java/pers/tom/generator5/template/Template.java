package pers.tom.generator5.template;

import pers.tom.generator.basic.exception.RenderException;
import pers.tom.generator5.renderdata.RenderData;
import pers.tom.generator5.renderresult.RenderResult;

/**
 * @author lijia
 * @description 模板
 * @date 2021-03-09 11:08
 */
public interface Template<DATA extends RenderData> {


    /**
     * 模板渲染逻辑
     * @param renderData 渲染数据
     * @return 渲染结果
     * @throws RenderException 渲染异常
     */
    RenderResult rendering(DATA renderData) throws RenderException;
}
