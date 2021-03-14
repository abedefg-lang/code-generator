package pers.tom.generator.basic;

import lombok.Getter;
import org.springframework.lang.NonNull;
import pers.tom.generator.basic.exception.DataFormatNotSupportException;
import pers.tom.generator.basic.renderdata.JavaFileRenderData;
import pers.tom.generator.basic.renderdata.RenderData;

import java.io.File;

/**
 * @author tom
 * @description java文件吸入路径获取器
 * @date 2021/3/13 11:58
 */
@Getter
public class JavaFileWritePathGetter implements BatchCodeGenerator.FileWritePathGetter {

    /**输出根路径*/
    private final String outputRootPath;

    public JavaFileWritePathGetter(@NonNull String outputRootPath) {
        this.outputRootPath = outputRootPath;
    }

    @Override
    public String getWritePath(RenderData renderData) {
        if(renderData instanceof JavaFileRenderData) {
            JavaFileRenderData data = (JavaFileRenderData) renderData;
            String separator = File.separator;
            return outputRootPath + separator + data.getPackageName().replace(".", separator) + separator + data.getClassName() + ".java";
        }
        throw new DataFormatNotSupportException(renderData);
    }
}
