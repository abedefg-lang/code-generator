package com.tom.code_generator.table.factory.reverse;

import com.tom.code_generator.table.TableInfo;
import com.tom.code_generator.table.factory.TableInfosFactory;
//import com.tom.code_generator.template.render.injector.TemplateRenderParamInjector;

import java.util.List;

/**
 * @author tom
 * @description 逆向的tableInfos工厂  可能没有表 只有实体类 需要通过解析实体类获取tableInfo
 *              由于entity已经存在了 只需要直接从dao开始生成
 *              但是会需要entity的包名与文件名  所以还需要实现injector将entity的信息注入进去
 * @date 2021/1/7 13:37
 */
public abstract class ReverseTableInfosFactory implements TableInfosFactory{

    private Class<?>[] entityClasses;

    @Override
    public List<TableInfo> getTableInfos() {
        return null;
    }


    /**
     * 解析实体类的方法交给子类取实现
     * @param entityClass entityClass
     * @return 返回TableInfo
     */
    protected abstract TableInfo parseEntityClass(Class<?> entityClass);


}
