package pers.tom.generator5.renderdata;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;

import java.util.Set;

/**
 * @author lijia
 * @description 渲染java代码数据
 * @date 2021-03-09 12:00
 */
@Data
@Accessors(chain = true)
public class JavaRenderData implements RenderData{

    /**包名*/
    private String packageName;

    /**import的内容*/
    private Set<String> imports;

    /**java文件类型*/
    private JavaFileType type;

    /**类名*/
    private String className;

    public JavaRenderData(@NonNull String packageName,
                          @NonNull JavaFileType type,
                          @NonNull String className){
        this.packageName = packageName;
        this.type = type;
        this.className = className;
    }

    @Override
    public String getGroupName() {
        return packageName;
    }

}

