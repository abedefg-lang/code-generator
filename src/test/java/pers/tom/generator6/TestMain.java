package pers.tom.generator6;


import org.junit.Test;
import pers.tom.generator6.template.FileTemplate;
import pers.tom.generator6.template.Template;
import pers.tom.generator6.template.engine.VelocityEngine;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lijia
 * @description
 * @date 2021-03-12 14:08
 */
public class TestMain {

    private final VelocityEngine engine = new VelocityEngine();

    @Test
    public void test(){

        //1，创建代码生成器
        CodeGenerator generator = new CodeGenerator();
        //添加模板
        generator.addTemplate(new FileTemplate("test", "src\\test\\resources\\test.vm", engine));
        //创建写入结果处理器
        WriteResultHandler handler = new WriteResultHandler(System.getProperty("user.dir"), true) {
            @Override
            protected String getWritePath(Template template, Map<String, Object> param) {
                return outputRootPath + File.separator + template.getName() + ".txt";
            }
        };
        //创建生成参数
        Map<String, Object> data = new HashMap<>();
        data.put("name", "tom2");
        data.put("age", 22);

        generator.generate(data, handler, "test");

    }
}
