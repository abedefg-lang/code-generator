package com.test;

import com.generator.builder.CodeGeneratorBuilder;
import com.generator.builder.TemplateCodeGeneratorBuilder;


import java.sql.SQLException;



public class Test {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InstantiationException {
        CodeGeneratorBuilder builder = new TemplateCodeGeneratorBuilder("generator.xml");
        builder.build().generate();
//        MysqlDataSource dataSource = new MysqlDataSource();

    }
}
