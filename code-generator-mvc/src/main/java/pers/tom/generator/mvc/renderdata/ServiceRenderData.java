package pers.tom.generator.mvc.renderdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.tom.generator.basic.renderdata.JavaFileRenderData;

/**
 * @author tom
 * @description service接口对应的渲染数据
 *              一个service接口对应一个entity
 * @date 2021/3/14 11:31
 */
@Getter
@Setter
@ToString
public class ServiceRenderData extends JavaFileRenderData {

    /**对应的entity*/
    private EntityRenderData entity;

    /**对应的mapper*/
    private MapperRenderData mapper;


    public ServiceRenderData(String packageName, String className) {
        super(packageName, className);
    }
}
