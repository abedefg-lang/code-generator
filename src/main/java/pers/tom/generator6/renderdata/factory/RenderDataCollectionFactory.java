package pers.tom.generator6.renderdata.factory;

import pers.tom.generator6.renderdata.RenderData;

import java.util.Collection;

/**
 * @author lijia
 * @description 渲染数据集合工厂
 * @date 2021-03-12 13:57
 */
public interface RenderDataCollectionFactory {


    Collection<RenderData> get();
}
