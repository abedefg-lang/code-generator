package pers.tom.generator5;


import org.junit.Test;
import pers.tom.generator5.exception.DataFormatNotSupportedException;

import pers.tom.generator5.template.filetemplate.FileTemplate;
import pers.tom.generator5.template.filetemplate.JavaFileTemplate;
import pers.tom.generator5.template.filetemplate.engine.VelocityEngine;


/**
 * @author lijia
 * @description
 * @date 2021-03-09 14:16
 */
public class Test01 {

    @Test
    public void test01() throws DataFormatNotSupportedException {
        FileTemplate testTemplate = new JavaFileTemplate("src/main/resources/generic-templates/simple.vm", new VelocityEngine(), "");
        testTemplate.rendering(() -> "");
    }
}
