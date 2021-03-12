package pers.tom.generator5.renderdata.factory;

import pers.tom.generator5.renderdata.RenderData;

import java.util.Collection;

/**
 * @author lijia
 * @description 渲染数据集合工厂
 * @date 2021-03-11 11:59
 */
public interface RenderDataCollectionFactory<DATA extends RenderData> {

    /**
     * 获取渲染数据集合
     * @return 返回collection
     */
    Collection<DATA> get();
}
