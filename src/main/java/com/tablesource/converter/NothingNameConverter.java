package com.tablesource.converter;

import com.utils.NameUtils;

/**
 * 不进行任何操作
 */
public class NothingNameConverter implements NameConverter{

    @Override
    public String getClassName(String tableName) {
        return NameUtils.initialUppercase(tableName);
    }

    @Override
    public String getFiledName(String columnName) {
        return columnName;
    }
}
