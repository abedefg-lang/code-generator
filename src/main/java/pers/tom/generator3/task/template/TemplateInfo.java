package pers.tom.generator3.task.template;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author tom
 * @description 模板配置
 * @date 2021/2/22 22:43
 */
@Data
@Accessors(chain = true)
public class TemplateInfo {

    /**文件名占位符*/
    public static final String FILE_NAME_PLACEHOLDER = "%s";

    /**模板名称*/
    private String name;

    /**模板路径*/
    private String templatePath;

    /**目标文件夹  由该模板生成的文件全部会存在该文件夹下*/
    private String targetFolder;

    /**文件命名风格  可使用 %s 来替换可变部分(比如 I%sService)*/
    private String fileNamingStyle;

    /**目标文件格式*/
    private String targetFileFormat;

    /**
     * 获取真实的文件名 使用部分文件名替换占位符部分
     * @param partialFileName 部分文件名
     * @return 返回真实文件名
     */
    public String getRealFileName(String partialFileName){

        return String.format(fileNamingStyle, partialFileName);
    }
}
