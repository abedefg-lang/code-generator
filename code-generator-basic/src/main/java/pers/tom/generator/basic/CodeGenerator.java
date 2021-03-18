package pers.tom.generator.basic;

import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.util.CollectionUtils;
import pers.tom.generator.basic.interceptor.TemplateRenderInterceptor;
import pers.tom.generator.basic.renderdata.RenderData;
import pers.tom.generator.basic.template.Template;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lijia
 * @description 代码生成器
 * @date 2021-03-12 13:21
 */
@Data
public class CodeGenerator {

    /**当生成的文件已经存在时 是否可以重写*/
    private boolean rewritable;

    /**渲染拦截器*/
    private List<TemplateRenderInterceptor> interceptorList;

    /**
     * 核心生成逻辑
     * @param template template
     * @param renderData renderData
     * @param writePath 写入路径
     */
    public void generate(@NonNull Template template,
                         @NonNull RenderData renderData,
                         @NonNull String writePath){

        //执行前置 渲染 后置
        if(this.applyPreRendering(template, renderData)){

            Object result = template.rendering(renderData.toMap());

            this.applyPostRendering(template, renderData, result);

            //写入代码
            this.writeCode(writePath, result.toString());
        }
    }

    /**
     * 添加拦截器
     * @param interceptor interceptor
     */
    public void addInterceptor(@NonNull TemplateRenderInterceptor interceptor){
        if(interceptorList == null){
            interceptorList = new ArrayList<>();
        }
        interceptorList.add(interceptor);
    }

    /**
     * 执行前置处理
     * @param template 模板
     * @param renderData 渲染数据
     * @return 返回true才执行渲染逻辑
     */
    private boolean applyPreRendering(Template template, RenderData renderData){

        if(!CollectionUtils.isEmpty(interceptorList)){
            for(TemplateRenderInterceptor interceptor : interceptorList){
                if(!interceptor.preRendering(template, renderData)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 执行后置处理
     * @param template 模板
     * @param renderData 渲染数据
     * @param result 渲染结果
     */
    private void applyPostRendering(Template template, RenderData renderData, Object result){

        if(!CollectionUtils.isEmpty(interceptorList)){
            for(TemplateRenderInterceptor interceptor : interceptorList){
                interceptor.postRendering(template, renderData, result);
            }
        }
    }

    /**
     * 将代码写入到指定目录
     * @param writePath 写入路径
     * @param content 内容
     */
    private void writeCode(String writePath, String content){

        File file = new File(writePath);
        //当文件不存在 或者 允许重写的时候才写入
        if(!file.exists() || rewritable){
            //如果父级文件夹不存在 进行创建
            if(!file.getParentFile().exists()){
                file.getParentFile().mkdirs();
            }
            //写入文件
            try(FileWriter writer = new FileWriter(file);
                BufferedWriter bw = new BufferedWriter(writer)){
                bw.write(content);
                bw.flush();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
