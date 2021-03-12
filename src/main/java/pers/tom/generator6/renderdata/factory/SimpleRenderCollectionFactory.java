package pers.tom.generator6.renderdata.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.tom.generator6.renderdata.RenderData;

import java.util.Collection;

/**
 * @author lijia
 * @description
 * @date 2021-03-12 14:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleRenderCollectionFactory implements RenderDataCollectionFactory{

    private Collection<RenderData> collection;

    @Override
    public Collection<RenderData> get() {
        return collection;
    }
}
