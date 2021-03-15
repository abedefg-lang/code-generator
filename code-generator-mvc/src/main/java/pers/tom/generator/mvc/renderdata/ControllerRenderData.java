package pers.tom.generator.mvc.renderdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.tom.generator.basic.renderdata.JavaFileRenderData;

/**
 * @author tom
 * @description controller对应的渲染数据
 * @date 2021/3/14 11:35
 */
@Getter
@Setter
@ToString
public class ControllerRenderData extends JavaFileRenderData {

    /**对应的entity*/
    private EntityRenderData entity;

    /**对应的service*/
    private ServiceRenderData service;


    public ControllerRenderData(String packageName, String className, ServiceRenderData service) {
        super(packageName, className);
        this.service = service;
        this.entity = service.getEntity();
    }
}
