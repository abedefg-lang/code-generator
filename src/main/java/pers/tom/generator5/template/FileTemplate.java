package pers.tom.generator5.template;

import lombok.Getter;
import org.springframework.lang.NonNull;
import pers.tom.generator5.renderdata.RenderData;
import pers.tom.generator5.template.engine.TemplateEngine;

import java.util.HashMap;
import java.util.Map;


/**
 * @author lijia
 * @description 文件形式的模板
 * @date 2021-03-09 11:13
 */
@Getter
public abstract class FileTemplate implements Template {

    /**文件模板路径  文件格式 xxx.渲染出的文件后缀.模板文件后缀(abc.java.vm)*/
    protected final String templatePath;

    /**模板引擎*/
    protected final TemplateEngine engine;

    public FileTemplate(@NonNull String templatePath,
                        @NonNull TemplateEngine engine){
        this.templatePath = templatePath;
        this.engine = engine;
    }

    @Override
    public String rendering(@NonNull RenderData renderData) {

        if(support(renderData)){
            return this.doRendering(renderData);
        }
        throw new RuntimeException(templatePath + " 不支持 " + renderData.getClass() + " 渲染数据");
    }

    /**
     * 获取渲染出的文件后缀
     * @return suffix
     */
    public String getTargetFileSuffix(){

        String[] pathSegment = templatePath.split("\\.");
        return pathSegment[1];
    }


    protected String doRendering(RenderData renderData){

        //构建map
        Map<String, Object> map = new HashMap<>(4);
        map.put("renderData", renderData);
        map.put("template", this);

        String content = engine.execute(templatePath, map);
        return content;
    }


}
