package pers.tom.generator.mvc.renderdata;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pers.tom.generator.basic.renderdata.JavaFileRenderData;

/**
 * @author lijia
 * @description
 * @date 2021-03-15 15:36
 */
@Getter
@Setter
@ToString
public class ServiceImplRenderData extends JavaFileRenderData {

    /**对应的entity*/
    private EntityRenderData entity;

    /**对应的mapper*/
    private MapperRenderData mapper;

    /**对应的service接口*/
    private ServiceRenderData service;

    public ServiceImplRenderData(String packageName, String className, ServiceRenderData service) {
        super(packageName, className);
        this.entity = service.getEntity();
        this.mapper = service.getMapper();
        this.service = service;
    }
}
