package pers.tom.generator5.renderdata.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.tom.generator5.renderdata.RenderData;

import java.util.Collection;

/**
 * @author lijia
 * @description 简单渲染数据集合工厂
 * @date 2021-03-12 11:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleRenderDataCollectionFactory<DATA extends RenderData> implements RenderDataCollectionFactory<DATA>{

    private Collection<DATA> collection;

    @Override
    public Collection<DATA> get() {
        return collection;
    }
}
