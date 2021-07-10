package pers.tom.generator6.template;

import org.springframework.lang.NonNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @author tom
 * @description 基于文件的模板
 * @date 2021/7/10 23:36
 */
public abstract class AbstractFileTemplate implements Template{

    /**模板名称*/
    protected final String name;

    /**模板路径*/
    protected final String templatePath;

    public AbstractFileTemplate(String name, String templatePath) {
        this.name = name;
        this.templatePath = templatePath;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getTemplatePath() {
        return templatePath;
    }

    /**
     * 渲染数据 返回结果
     * @param param 渲染参数
     * @return 渲染结果
     */
    public String render(Map<String, Object> param){
        StringWriter writer = new StringWriter();
        render(param, writer);
        return writer.toString();
    }

    /**
     * 将结果写入到指定文件
     * @param param 渲染参数
     * @param file file
     */
    public void render(Map<String, Object> param, @NonNull File file){
        try(FileWriter writer = new FileWriter(file)){
            render(param, writer);
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "AbstractFileTemplate{" +
                "name='" + name + '\'' +
                ", templatePath='" + templatePath + '\'' +
                '}';
    }
}
