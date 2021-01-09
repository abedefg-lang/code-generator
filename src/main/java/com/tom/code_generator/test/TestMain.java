package com.tom.code_generator.test;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.tom.code_generator.config.GlobalConfig;
import com.tom.code_generator.CodeGenerator;
import com.tom.code_generator.table.factory.jdbc.TableInfosFactory4JDBC;
import com.tom.code_generator.table.factory.jdbc.dialect.MySqlDialect;
import com.tom.code_generator.template.TemplateInfo;
import com.tom.code_generator.template.engine.VelocityTemplateEngine;

/**
 * @author tom
 * @description
 * @date 2021/1/7 21:52
 */
public class TestMain {

    public static void main(String[] args) {
        CodeGenerator codeGenerator = new CodeGenerator();

        //设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("tom");
        globalConfig.setParentPackage("com.test");
        codeGenerator.setGlobalConfig(globalConfig);

        //设置TableInfosFactory
        TableInfosFactory4JDBC factory = new TableInfosFactory4JDBC();
        factory.setConvertToHump(true);
        factory.setDialect(new MySqlDialect());
        factory.setNamePatterns(".*");//设置匹配规则 全表匹配
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/test?serverTimezone=CST&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        factory.setDataSource(dataSource);
        codeGenerator.setTableInfosFactory(factory);

        //设置模板渲染器
        TemplateRenderer2 renderer = new TemplateRenderer2(new VelocityTemplateEngine());
        codeGenerator.setTemplateRenderer(renderer);

        //添加使用的模板
        codeGenerator.addTemplateConfig(new TemplateInfo("entity", "src\\main\\resources\\templates\\entity.java.vm"));

        //生成
        codeGenerator.generate();
    }
}
