package pers.tom.generator.basic.renderdata;

import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import pers.tom.generator.basic.utils.NameUtils;

import java.util.HashSet;
import java.util.Set;

/**
 * @author tom
 * @description java文件渲染数据
 * @date 2021/3/12 23:05
 */
@Data
public class JavaFileRenderData implements RenderData{

    /**包名*/
    private final String packageName;

    /**import的内容*/
    private Set<String> imports;

    /**java文件类型*/
    private JavaFileType fileType;

    /**类名*/
    private final String className;

    /**小驼峰类名*/
    private final String smallHumpClassName;


    public JavaFileRenderData(@NonNull String packageName,
                              @NonNull String className){

        this.packageName = packageName;
        this.imports = new HashSet<>();
        this.fileType = JavaFileType.CLASS;
        this.className = className;
        this.smallHumpClassName = NameUtils.initialLowercase(className);
    }

    /**
     * 添加需要导入的class
     */
    public void addImportClass(@NonNull Class<?> clazz){
        this.addImportClass(clazz.getName());
    }

    /**
     * 添加需要导入的class
     */
    public void addImportClass(@NonNull String className){
        if(isNeedImport(className)){
            imports.add(className);
        }
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
            return !StringUtils.isEmpty(otherPackage)
                    && !otherPackage.equals(packageName)
                    && !"java.lang".equals(otherPackage);
        }
        return false;
    }

}
