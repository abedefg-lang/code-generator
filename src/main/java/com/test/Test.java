package com.test;


import com.generator.CodeGenerator;
import com.generator.builder.CodeGeneratorBuilder;
import com.generator.builder.XmlClassPathTemplateCodeGeneratorBuilder;

public class Test {
    public static void main(String[] args) {
        CodeGeneratorBuilder builder = new XmlClassPathTemplateCodeGeneratorBuilder("generator.xml");
        CodeGenerator codeGenerator = builder.build();
        codeGenerator.generate();
        //如何获取一个数组或者一个集合中的存放的类型
    }
}
