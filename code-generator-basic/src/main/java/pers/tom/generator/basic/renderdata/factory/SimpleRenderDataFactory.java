package pers.tom.generator.basic.renderdata.factory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pers.tom.generator.basic.renderdata.RenderData;

import java.util.Collection;
import java.util.Collections;

/**
 * @author lijia
 * @description 简单渲染数据工厂
 * @date 2021-03-19 18:15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimpleRenderDataFactory implements RenderDataFactory{

    private Collection<RenderData> renderDataCollection;

    @Override
    public Collection<RenderData> get() {
        return renderDataCollection == null ? Collections.emptyList() : renderDataCollection;
    }
}
