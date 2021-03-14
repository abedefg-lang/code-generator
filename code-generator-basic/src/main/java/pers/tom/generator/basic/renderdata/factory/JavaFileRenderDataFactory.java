package pers.tom.generator.basic.renderdata.factory;

import lombok.Data;
import org.springframework.lang.NonNull;

/**
 * @author tom
 * @description
 * @date 2021/3/12 23:12
 */
@Data
public abstract class JavaFileRenderDataFactory implements RenderDataFactory {

    /**类名占位符*/
    public static final String CLASS_NAME_PLACE_HOLDER = "%s";

    /**包名*/
    protected String packageName;

    /**命名风格*/
    protected String namingStyle;


    public JavaFileRenderDataFactory(@NonNull String packageName,
                                     @NonNull String namingStyle){

        this.packageName = packageName;
        this.namingStyle = namingStyle;
    }

}

