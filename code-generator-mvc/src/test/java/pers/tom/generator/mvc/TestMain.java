package pers.tom.generator.mvc;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.junit.Test;

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
        dataSource.setUrl("");
        dataSource.setUser("root");
        dataSource.setPassword("root");
        codeGenerator.setTableInfoFactory(new TableInfoFactory4Jdbc(dataSource));
        codeGenerator.setTemplateConfig(MvcTemplateConfig.getDefaultConfig());

    }
}
