package com.tablesource.converter;

/**
 * 将表名转化成类名  字段名转化成属性名
 */
public interface NameConverter {

    NameConverter NOTHING_CONVERTER = new NothingNameConverter();

    NameConverter CAMEL_CONVERTER = new CamelNameConverter();


    /**
     * 获取类名
     * @param tableName 表名
     * @return 返回className
     */
    String getClassName(String tableName);

    /**
     * 获取属性名
     * @param columnName 字段名
     * @return 返回属性名
     */
    String getFiledName(String columnName);

}
