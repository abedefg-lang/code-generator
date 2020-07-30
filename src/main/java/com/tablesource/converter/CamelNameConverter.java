package com.tablesource.converter;

import com.utils.NameUtils;

/**
 * 转换成驼峰式
 */
public class CamelNameConverter implements NameConverter{


    private static final String REGEX = "_";


    @Override
    public String getClassName(String tableName) {
        return NameUtils.convertClassName(tableName, REGEX);
    }

    @Override
    public String getFiledName(String columnName) {
        return NameUtils.convertCamel(columnName, REGEX);
    }
}
