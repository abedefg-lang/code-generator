package pers.tom.generator5.java.renderdata;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import pers.tom.generator5.renderdata.RenderData;

import java.util.Set;

/**
 * @author lijia
 * @description 渲染java代码数据
 * @date 2021-03-09 12:00
 */
@Data
@Accessors(chain = true)
public class JavaFileRenderData implements RenderData {

    /**包名*/
    @NonNull
    private String packageName;

    /**import的内容*/
    @Nullable
    private Set<String> imports;

    /**java文件类型*/
    @NonNull
    private JavaFileType type;

    /**类名*/
    @NonNull
    private String className;


    public JavaFileRenderData(String packageName, String className){

        this.packageName = packageName;
        this.type = JavaFileType.CLASS;
        this.className = className;
    }

    /**
     * 判断指定的package是否需要导入
     * @param otherClass otherClass
     * @return 返回boolean
     */
    protected boolean isNeedImport(String otherClass){

        if(!StringUtils.isEmpty(otherClass)){
            //获取包名
            String otherPackage = ClassUtils.getPackageName(otherClass);
            //当包与当前文件不在同一个包 并且 不是java.lang包下的 才需要导包
            return !otherPackage.equals(packageName) && !"java.lang".equals(otherPackage);
        }
        return false;
    }

}

