package com.test;


import com.generator.CodeGenerator;
import com.generator.builder.CodeGeneratorBuilder;
import com.generator.builder.XmlTemplateCodeGeneratorBuilder;
import com.utils.file.FileUtils;
import com.utils.reflect.ReflectUtils;

import java.net.URL;

public class Test {
    public static void main(String[] args) {
        CodeGeneratorBuilder builder = new XmlTemplateCodeGeneratorBuilder("classpath:generator.xml");
        CodeGenerator codeGenerator = builder.build();
        codeGenerator.generate();

    }
}
