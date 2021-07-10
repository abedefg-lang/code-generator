package pers.tom.generator6;


import org.junit.Test;

import pers.tom.generator6.template.Template;
import pers.tom.generator6.template.VelocityTemplate;

import java.util.Map;

/**
 * @author lijia
 * @description
 * @date 2021-03-12 14:08
 */
public class TestMain {

    @Test
    public void test(){

        //1，创建代码生成器
        CodeGenerator generator = new CodeGenerator();
        generator.addTemplate(new VelocityTemplate("test1", "src\\test\\resources\\test.vm"))
                .setResultHandler((template, param, result) -> System.out.println("result : "+result));

        generator.generate(null);
    }
}
