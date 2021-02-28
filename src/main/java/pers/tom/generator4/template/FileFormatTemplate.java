package pers.tom.generator4.template;

import org.springframework.lang.NonNull;

/**
 * @author tom
 * @description 文件形式的模板
 * @date 2021/2/28 22:56
 */
public abstract class FileFormatTemplate implements Template{

    /**模板名称*/
    private final String name;

    /**模板路径*/
    private final String templatePath;

    public FileFormatTemplate(@NonNull String name,
                              @NonNull String templatePath){
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
}
