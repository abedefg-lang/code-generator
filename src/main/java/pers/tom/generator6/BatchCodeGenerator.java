package pers.tom.generator6;

import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import pers.tom.generator6.renderdata.RenderData;
import pers.tom.generator6.renderdata.factory.RenderDataCollectionFactory;
import pers.tom.generator6.template.FileTemplate;

import java.util.Collection;

/**
 * @author lijia
 * @description 批量代码生成器
 * @date 2021-03-12 13:41
 */

public class BatchCodeGenerator extends CodeGenerator{

    /**
     * 批量生成
     * @param template 模板
     * @param collection 渲染数据
     * @param writePathGetter 由于文件是批量写入 每一个文件的写入路径都不同 所以需要一个获取路径的策略
     */
    public void batchGenerate(@NonNull FileTemplate template,
                              @NonNull Collection<RenderData> collection,
                              @NonNull FileWritePathGetter writePathGetter){

        for(RenderData data : collection){
            String writePath = writePathGetter.getWritePath(data);
            generate(template, data, writePath);
        }

    }


    public void batchGenerate(@NonNull FileTemplate template,
                              @NonNull RenderDataCollectionFactory factory,
                              @NonNull FileWritePathGetter writePathGetter){

        this.batchGenerate(template, factory.get(), writePathGetter);
    }


    /**
     * 文件路径获取器
     */
    public interface FileWritePathGetter{

        /**
         * 获取写入路径
         * @param renderData 渲染数据
         * @return 返回路径
         */
        String getWritePath(@NonNull RenderData renderData);
    }
}
