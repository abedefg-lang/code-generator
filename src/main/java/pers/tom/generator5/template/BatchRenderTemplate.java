package pers.tom.generator5.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import pers.tom.generator6.exception.RenderException;
import pers.tom.generator5.renderdata.RenderData;
import pers.tom.generator5.renderdata.factory.RenderDataCollectionFactory;
import pers.tom.generator5.renderresult.RenderResult;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lijia
 * @description 可批量渲染的模板
 * @date 2021-03-12 10:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchRenderTemplate<DATA extends RenderData> implements Template<DATA> {

    /**真正执行渲染的模板*/
    private Template<DATA> template;

    /**创建渲染数据的工厂*/
    private RenderDataCollectionFactory<DATA> factory;

    @Override
    public RenderResult rendering(DATA renderData) throws RenderException {

        Assert.notNull(template, "template不能为null");
        return template.rendering(renderData);
    }

    /**
     * 批量渲染
     * 通过factory获取渲染数据集合
     * @return 返回渲染结果集合
     */
    public List<RenderResult> batchRendering() throws RenderException{

        Assert.notNull(factory, "factory不能为null");
        return batchRendering(factory.get());
    }

    /**
     * 批量渲染
     * @param collection 渲染数据集合
     * @return 返回渲染结果集合
     */
    public List<RenderResult> batchRendering(Collection<DATA> collection) throws RenderException{

        if(!CollectionUtils.isEmpty(collection)){
            return collection.stream().map(this::rendering).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }


}
