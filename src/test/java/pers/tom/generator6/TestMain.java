package pers.tom.generator6;

import org.junit.Test;
import pers.tom.generator6.factory.TestRenderDataCollectionFactory;
import pers.tom.generator.basic.template.FileTemplate;
import pers.tom.generator.basic.template.engine.VelocityEngine;

/**
 * @author lijia
 * @description
 * @date 2021-03-12 14:08
 */
public class TestMain {

    @Test
    public void test(){
//        CodeGenerator codeGenerator = new CodeGenerator();
//        FileTemplate testTemplate = new FileTemplate("test", "src/test/resources/test.vm", new VelocityEngine());
//        TestRenderData testRenderData = new TestRenderData("tom", "18");
//        codeGenerator.generate(testTemplate, testRenderData, "src/test/java/pers/tom/generator6/codes/test.txt");

        BatchCodeGenerator batchCodeGenerator = new BatchCodeGenerator();

        FileTemplate testTemplate = new FileTemplate("test", "src/test/resources/test.vm", new VelocityEngine());
        TestRenderDataCollectionFactory factory = new TestRenderDataCollectionFactory();
        batchCodeGenerator.batchGenerate(testTemplate, factory, renderData -> {
            TestRenderData testRenderData = (TestRenderData) renderData;
            return "src/test/java/pers/tom/generator6/codes/"+testRenderData.getName()+".txt";
        });
    }
}
