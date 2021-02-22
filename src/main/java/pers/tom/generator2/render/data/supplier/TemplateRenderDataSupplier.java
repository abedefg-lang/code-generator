package pers.tom.generator2.render.data.supplier;

import pers.tom.generator2.render.data.TemplateRenderData;
import pers.tom.generator2.template.TemplateInfo;

import java.util.List;

/**
 * @author lijia
 * @description 模板渲染数据供应商
 * @date 2021/2/22 22:58
 */
public interface TemplateRenderDataSupplier {

    /**
     * 获取渲染指定模板需要的数据
     * @param templateInfo 模板信息
     * @return 返回list
     */
    List<TemplateRenderData> getRenderData(TemplateInfo templateInfo);
}
