package pers.tom.generator6.factory;

import pers.tom.generator6.TestRenderData;
import pers.tom.generator6.renderdata.RenderData;
import pers.tom.generator6.renderdata.factory.RenderDataCollectionFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author lijia
 * @description
 * @date 2021-03-12 14:15
 */
public class TestRenderDataCollectionFactory implements RenderDataCollectionFactory {

    @Override
    public Collection<RenderData> get() {
        List<RenderData> list = new ArrayList<>();
        list.add(new TestRenderData("zhangsan", "18"));
        list.add(new TestRenderData("lisi", "20"));
        list.add(new TestRenderData("wangwu", "22"));
        return list;
    }
}
