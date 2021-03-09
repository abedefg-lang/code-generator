package pers.tom.generator5;


import org.junit.Test;
import pers.tom.generator5.renderdata.JavaFileType;
import pers.tom.generator5.renderdata.JavaRenderData;
import pers.tom.generator5.template.JavaFileTemplate;
import pers.tom.generator5.template.engine.VelocityEngine;

/**
 * @author lijia
 * @description
 * @date 2021-03-09 14:16
 */
public class Test01 {

    @Test
    public void test01(){
        JavaFileTemplate javaFileTemplate = new JavaFileTemplate("src/main/resources/generic-templates/simple.java.vm", new VelocityEngine());
        JavaRenderData renderData = new JavaRenderData("com", JavaFileType.ANNOTATION, "TestMain");
        String content = javaFileTemplate.rendering(renderData);
        System.out.println(content);
    }
}
