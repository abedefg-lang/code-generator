package pers.tom.generator5;

import org.junit.Test;
import pers.tom.generator5.java.renderdata.EntityRenderData;

import pers.tom.generator5.java.renderdata.factory.EntityRenderDataCollectionFactory;
import pers.tom.generator5.java.template.JavaFileTemplate;

import pers.tom.generator5.template.BatchRenderTemplate;
import pers.tom.generator6.template.engine.VelocityEngine;



/**
 * @author lijia
 * @description
 * @date 2021-03-09 14:16
 */
public class Test01 {

    @Test
    public void test01() {

        JavaFileTemplate<EntityRenderData> javaFileTemplate = new JavaFileTemplate<>("src/main/resources/generic-templates/entity.vm", new VelocityEngine());
        EntityRenderDataCollectionFactory factory = new EntityRenderDataCollectionFactory();
        BatchRenderTemplate<EntityRenderData> batchRenderTemplate = new BatchRenderTemplate<>(javaFileTemplate, factory);
//        List<RenderResult> results = batchRenderTemplate.batchRendering();
//
//        System.out.println(results);
    }
}
