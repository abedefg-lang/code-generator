package com.tom.code_generator;

import com.tom.code_generator.config.GlobalConfig;
import com.tom.code_generator.table.TableInfo;
import com.tom.code_generator.table.factory.TableInfosFactory;
import com.tom.code_generator.template.TemplateInfo;
import com.tom.code_generator.template.TemplateRenderConfig;
import com.tom.code_generator.template.renderer.TemplateRenderer;
import com.tom.code_generator.template.interceptor.ImproveRenderConfigInterceptor;
import com.tom.code_generator.template.interceptor.TemplateRenderInterceptor;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author tom
 * @description 基于模板进行生成代码
 * @date 2021/1/4 22:38
 */
@Data
@Accessors(chain = true)
public class CodeGenerator {

    /**全局配置*/
    private GlobalConfig globalConfig;

    /**tableInfosFactory*/
    private TableInfosFactory tableInfosFactory;

    /**模板渲染器*/
    private TemplateRenderer templateRenderer;

    /**需要使用到的模板*/
    private List<TemplateInfo> templateConfigList;

    /**渲染拦截器*/
    private List<TemplateRenderInterceptor> renderInterceptors;

    public CodeGenerator(){
        this.init();
    }

    /**
     * 核心生成方法
     */
    public void generate() {

        //创建之前检查属性是否合法
        this.preGenerate();

        //执行生成逻辑
        if(globalConfig.isOpen() && !CollectionUtils.isEmpty(templateConfigList)){

            List<TableInfo> tableInfos = tableInfosFactory.getTableInfos();
            if(!CollectionUtils.isEmpty(tableInfos)){

                //进行渲染  需要构建渲染参数
                TemplateRenderConfig config = new TemplateRenderConfig(globalConfig);
                for(TableInfo table : tableInfos){
                    config.setTable(table);
                    for(TemplateInfo template : templateConfigList){
                        config.setTemplate(template);

                        //渲染前置处理  渲染  渲染后置处理
                        if(this.applyPreRender(config)){
                            Object renderResult = templateRenderer.render(template.getTemplatePath(), config);
                            this.applePostRender(renderResult, config);
                        }
                    }
                }
            }
        }
    }

    /**
     * 添加模板配置
     * @param templateConfig 模板配置对象
     */
    public CodeGenerator addTemplateConfig(TemplateInfo templateConfig){
        if(templateConfigList == null){
            templateConfigList = new ArrayList<>();
        }
        this.templateConfigList.add(templateConfig);
        return this;
    }

    /**
     * 添加拦截器
     * @param interceptor 拦截器
     */
    public CodeGenerator addInterceptor(TemplateRenderInterceptor interceptor){
        if(renderInterceptors == null){
            renderInterceptors = new ArrayList<>(4);
        }
        this.renderInterceptors.add(interceptor);
        return this;
    }

    /**
     * 初始化
     */
    protected void init(){

        this.addInterceptor(new ImproveRenderConfigInterceptor());
    }

    /**
     * 生成前置逻辑
     */
    protected void preGenerate(){

        //检查tableInfosFactory
        Objects.requireNonNull(tableInfosFactory, "tableInfosFactory不能为null");

        //检查templateRender
        Objects.requireNonNull(templateRenderer, "templateRenderer不能为null");

        //如果globalConfig为null 获取默认的globalConfig
        if(globalConfig == null){
            globalConfig = new GlobalConfig();
        }
    }

    /**
     * 执行渲染前置处理
     * @param config 渲染配置
     * @return 返回是否能够执行渲染逻辑
     */
    private boolean applyPreRender(TemplateRenderConfig config){

        if(!CollectionUtils.isEmpty(renderInterceptors)){
            for(TemplateRenderInterceptor interceptor : renderInterceptors){

                //当某个拦截器返回false的时候直接返回
                if(!interceptor.preRender(config)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 执行渲染后置处理
     * @param renderResult 渲染结果
     * @param config 渲染配置
     */
    private void applePostRender(Object renderResult, TemplateRenderConfig config){

        if(!CollectionUtils.isEmpty(renderInterceptors)){
            for(TemplateRenderInterceptor interceptor : renderInterceptors){
                interceptor.postRender(renderResult, config);
            }
        }
    }

}
