package com.test;

import com.generator.builder.CodeGeneratorBuilder;
import com.generator.builder.TemplateCodeGeneratorBuilder;



public class Test {
    public static void main(String[] args) throws Exception{
        CodeGeneratorBuilder builder = new TemplateCodeGeneratorBuilder("generator.xml");
        builder.build().generate();

    }
}
