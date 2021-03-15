package pers.tom.generator.mvc;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.Test;

import pers.tom.generator.basic.JavaFileWritePathGetter;
import pers.tom.generator.mvc.config.MvcTemplateConfig;
import pers.tom.generator.mvc.table.jdbc.TableInfoFactory4Jdbc;


/**
 * @author tom
 * @description
 * @date 2021/3/14 0:05
 */
public class TestMain {

    @Test
    public void test001(){

        MvcCodeGenerator codeGenerator = new MvcCodeGenerator();

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/doc_warehouse?serverTimezone=CST&useUnicode=true&characterEncoding=utf-8&useSSL=false");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        TableInfoFactory4Jdbc factory4Jdbc = new TableInfoFactory4Jdbc(dataSource);
        factory4Jdbc.setAllTable(true);
        codeGenerator.setTableInfoFactory(factory4Jdbc);

        codeGenerator.setTemplateConfig(MvcTemplateConfig.getMybatisPlusStyleConfig());

        codeGenerator.setWritePathGetter(new JavaFileWritePathGetter("src\\test\\java"));

        codeGenerator.generate();



    }
}
