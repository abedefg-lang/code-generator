package pers.tom.generator2;

import pers.tom.generator.template.TemplateInfo;
import pers.tom.generator2.render.data.supplier.TemplateRenderDataSupplier;
import pers.tom.generator2.render.handler.TemplateRenderResultHandler;
import pers.tom.generator2.render.renderer.TemplateRenderer;

import java.util.List;

/**
 * @author tom
 * @description 基于模板生成代码
 * @date 2021/2/22 22:28
 */
public class CodeGenerator {

    /**所需的模板信息*/
    private List<TemplateInfo> templateInfos;

    /**渲染数据供应商*/
    private TemplateRenderDataSupplier renderDataSupplier;

    /**渲染器*/
    private TemplateRenderer renderer;

    /**渲染结果处理器*/
    private TemplateRenderResultHandler renderResultHandler;

    /**
     * 核心生成方法
     */
    public void generate(){

    }

}
