package com.test;


import com.generator.CodeGenerator;
import com.generator.builder.CodeGeneratorBuilder;
import com.generator.builder.XmlTemplateCodeGeneratorBuilder;

public class Test {
    public static void main(String[] args) throws Exception {

        CodeGeneratorBuilder builder = new XmlTemplateCodeGeneratorBuilder("classpath:generator.xml");
        CodeGenerator codeGenerator = builder.build();

        codeGenerator.generate();

    }
}
