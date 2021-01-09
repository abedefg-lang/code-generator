package pers.tom.generator.test;

import com.mysql.cj.jdbc.MysqlDataSource;
import pers.tom.generator.config.GlobalConfig;
import pers.tom.generator.TemplateCodeGenerator;
import pers.tom.generator.table.factory.jdbc.TableInfosFactory4JDBC;
import pers.tom.generator.table.factory.jdbc.dialect.MySqlDialect;
import pers.tom.generator.template.TemplateInfo;
import pers.tom.generator.template.interceptor.WritableRenderInterceptor;
import pers.tom.generator.template.renderer.VelocityRenderer;

/**
 * @author tom
 * @description
 * @date 2021/1/7 21:52
 */
public class TestMain {

    public static void main(String[] args) {
        TemplateCodeGenerator codeGenerator = new TemplateCodeGenerator();

        //设置全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("tom");
        globalConfig.setParentPackage("com.test");
        globalConfig.setOverwriteFile(true);
        globalConfig.setMybatisPlusModel(true);
        globalConfig.setSpringModel(true);
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
        codeGenerator.setTemplateRenderer(new VelocityRenderer());

        //添加写入文件拦截器
        codeGenerator.addInterceptor(new WritableRenderInterceptor());

        //添加使用的模板
        TemplateInfo entity = new TemplateInfo("entity", "src\\main\\resources\\generic-templates\\entity.vm");
        entity.putProperty("lombok", true);

        codeGenerator.addTemplateConfig(entity);
        codeGenerator.addTemplateConfig(new TemplateInfo("mapper", "src\\main\\resources\\generic-templates\\mapper.vm"));
        codeGenerator.addTemplateConfig(new TemplateInfo("service", "src\\main\\resources\\generic-templates\\service.vm"));
        codeGenerator.addTemplateConfig(new TemplateInfo("serviceImpl", "src\\main\\resources\\generic-templates\\serviceImpl.vm"));
        codeGenerator.addTemplateConfig(new TemplateInfo("controller", "src\\main\\resources\\generic-templates\\controller.vm"));
        codeGenerator.addTemplateConfig(new TemplateInfo("mapper", "src\\main\\resources\\generic-templates\\mapper.vm"));

        //生成
        codeGenerator.generate();
    }
}
