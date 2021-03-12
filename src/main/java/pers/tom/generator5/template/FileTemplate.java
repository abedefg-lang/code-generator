package pers.tom.generator5.template;

import lombok.Getter;
import org.springframework.lang.NonNull;
import pers.tom.generator5.exception.RenderException;
import pers.tom.generator5.renderdata.RenderData;
import pers.tom.generator5.renderresult.FileRenderResult;
import pers.tom.generator5.template.engine.TemplateEngine;

import java.util.HashMap;
import java.util.Map;


/**
 * @author lijia
 * @description 文件形式的模板
 * @date 2021-03-09 11:13
 */
@Getter
public abstract class FileTemplate<DATA extends RenderData> implements Template<DATA> {

    /**文件模板路径 */
    @NonNull
    protected final String templatePath;

    /**模板引擎*/
    @NonNull
    protected final TemplateEngine engine;

    /**文件输出根路径*/
    @NonNull
    protected final String outputRootPath;

    /**参数map 可以添加自定的参数*/
    protected final Map<String, Object> paramMap;


    public FileTemplate(String templatePath, TemplateEngine engine, String outputRootPath){

//        Assert.notNull(templatePath, "templatePath不能为null");
//        Assert.notNull(engine, "engine不能为null");
//        Assert.notNull(outputRootPath, "outputRootPath不能为null");

        this.templatePath = templatePath;
        this.engine = engine;
        this.outputRootPath = outputRootPath;
        this.paramMap = new HashMap<>();
    }

    @Override
    public FileRenderResult rendering(@NonNull DATA renderData) throws RenderException {

        String writePath = this.getWritePath(renderData);
        return new FileRenderResult(this.executeEngine(renderData), writePath);
    }

    /**
     * 添加参数
     * @param key key
     * @param value value
     */
    public void putParam(@NonNull String key,
                         @NonNull Object value){

        paramMap.put(key, value);
    }


    /**
     * 获取写入路径
     * @param renderData 渲染数据
     * @return 返回路径
     */
    protected abstract String getWritePath(DATA renderData);

    /**
     * 执行模板引擎
     * @param renderData 渲染数据
     * @return 返回内容
     */
    protected String executeEngine(DATA renderData){

        //构建map
        paramMap.put("renderData", renderData);
        paramMap.put("currentTemplate", this);

        return engine.execute(templatePath, paramMap);
    }


}
