package pers.tom.generator.table.factory.reverse;

import pers.tom.generator.table.TableInfo;
import pers.tom.generator.table.factory.TableInfosFactory;
import pers.tom.generator.table.factory.reverse.parser.EntityClassParser;
import pers.tom.generator.template.TemplateRenderConfig;
import pers.tom.generator.template.interceptor.TemplateRenderInterceptor;

import java.util.List;

/**
 * @author tom
 * @description 逆向的tableInfos工厂  可能没有表 只有实体类 需要通过解析实体类获取tableInfo
 *              由于entity已经存在了 只需要直接从mapper开始生成
 *              但是会需要entity的包名与文件名  需要在渲染前添加进entity的包名
 * @date 2021/1/7 13:37
 */
public class ReverseTableInfosFactory implements TableInfosFactory, TemplateRenderInterceptor {

    /**entityClasses*/
    private List<Class<?>> entityClasses;

    /**实体类解析器*/
    private EntityClassParser entityParser;

    /**需要知道其它模板使用获取entity信息用的id*/
    private String entityId;

    @Override
    public List<TableInfo> getTableInfos() {
        return null;
    }

    @Override
    public boolean preRender(TemplateRenderConfig config) {
        return false;
    }
}
