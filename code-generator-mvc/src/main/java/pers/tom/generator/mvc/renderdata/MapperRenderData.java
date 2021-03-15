package pers.tom.generator.mvc.renderdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.tom.generator.basic.renderdata.JavaFileRenderData;

/**
 * @author tom
 * @description mapper渲染数据
 *              一个mapper对应一个entity
 * @date 2021/3/14 11:29
 */
@Getter
@Setter
@ToString
public class MapperRenderData extends JavaFileRenderData {

    /**对应的entity*/
    private EntityRenderData entity;


    public MapperRenderData(String packageName, String className, EntityRenderData entity) {
        super(packageName, className);
        this.entity = entity;
    }

}
