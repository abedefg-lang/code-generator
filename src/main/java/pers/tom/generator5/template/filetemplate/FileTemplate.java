package pers.tom.generator5.template.filetemplate;

import lombok.Getter;
import org.springframework.lang.NonNull;
import pers.tom.generator5.exception.DataFormatNotSupportedException;
import pers.tom.generator5.template.filetemplate.engine.TemplateEngine;
import pers.tom.generator5.renderdata.RenderData;
import pers.tom.generator5.renderresult.FileRenderResult;
import pers.tom.generator5.template.Template;

import java.util.HashMap;
import java.util.Map;


/**
 * @author lijia
 * @description 文件形式的模板
 * @date 2021-03-09 11:13
 */
@Getter
public abstract class FileTemplate implements Template {

    /**文件模板路径 */
    protected final String templatePath;

    /**模板引擎*/
    protected final TemplateEngine engine;

    /**文件输出根路径*/
    protected final String outputRootPath;

    public FileTemplate(@NonNull String templatePath,
                        @NonNull TemplateEngine engine,
                        @NonNull String outputRootPath){
        this.templatePath = templatePath;
        this.engine = engine;
        this.outputRootPath = outputRootPath;
    }

    @Override
    public FileRenderResult rendering(@NonNull RenderData renderData) throws DataFormatNotSupportedException {

        if(support(renderData)){
            String writePath = this.getWritePath(renderData);
            return new FileRenderResult(this.executeEngine(renderData), writePath);
        }
        throw new DataFormatNotSupportedException(this, renderData);
    }

    /**
     * 获取写入路径
     * @param renderData 渲染数据
     * @return 返回路径
     */
    protected abstract String getWritePath(RenderData renderData);

    /**
     * 执行模板引擎
     * @param renderData 渲染数据
     * @return 返回内容
     */
    protected String executeEngine(RenderData renderData){

        //构建map
        Map<String, Object> map = new HashMap<>(4);
        map.put("renderData", renderData);
        map.put("template", this);

        return engine.execute(templatePath, map);
    }





}
