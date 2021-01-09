package pers.tom.generator.template;

import pers.tom.generator.utils.NameUtils;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tom
 * @description 模板配置
 * @date 2021/1/4 22:24
 */
@Data
@Accessors(chain = true)
public class TemplateInfo {

    public static final String RESOURCE_NAME_PLACEHOLDER = "%s";

    /**模板唯一标识*/
    private String id;

    /**模板文件路径*/
    private String templatePath;

    /**该模板生成的文件存放的目标包名*/
    private String targetPackage;

    /**该模板生成的文件名  使用占位符替换类名*/
    private String targetFileName;

    /**生成文件的后缀名*/
    private String targetFileSuffix;

    /**存储自定义的配置信息*/
    private Map<String, Object> propertyMap;

    public TemplateInfo(String id, String templatePath){

        //默认文件后缀为java
        this(id, templatePath, "java");
    }

    public TemplateInfo(String id, String templatePath, String targetFileSuffix){

        //目标包名 目标文件名 与id相同
        this.id = id;
        this.targetPackage = id;
        this.targetFileName = RESOURCE_NAME_PLACEHOLDER+ NameUtils.initialUppercase(id);
        this.templatePath = templatePath;
        this.targetFileSuffix = targetFileSuffix;
    }


    public TemplateInfo putProperty(String key, Object value){
        if (propertyMap == null) {
            propertyMap = new HashMap<>(8);
        }
        propertyMap.put(key, value);
        return this;
    }

    /**
     * 获取真正的文件名(替换之后)
     * @param resourceName 替换的资源名称
     * @return 返回替换之后的名称
     */
    public String getRealFileName(String resourceName){
        return String.format(targetFileName, resourceName);
    }

    /**
     * 获取完整的包名
     * @param parentPackage 父级包
     * @return 返回完整包名
     */
    public String getCompletePackage(String parentPackage){

        //如果为null或者空串不进行处理
        return StringUtils.isEmpty(parentPackage) ? targetPackage : parentPackage+"."+targetPackage;
    }

}
