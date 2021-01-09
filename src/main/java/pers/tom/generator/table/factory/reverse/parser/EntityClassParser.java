package pers.tom.generator.table.factory.reverse.parser;

import pers.tom.generator.table.TableInfo;

/**
 * @author lijia
 * @description entity class解析器  自定义解析策略
 * @date 2021/1/10 0:15
 */
public interface EntityClassParser {

    /**
     * 将实体类Class解析成tableInfo
     * @param entityClass 实体类
     * @return 返回tableInfo
     */
    TableInfo parse(Class<?> entityClass);
}
