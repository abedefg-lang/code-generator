package com.tablesource;

import com.tablesource.info.TableInfo;

import java.util.List;

public interface TableSource {

    /**
     * 获取指定的table的信息
     * @param tableNames 指定的表名
     * @param convertCamel 是否将数据库的字段  表名 转换为驼峰式
     * @return 返回list
     */
    List<TableInfo> getTableInfos(boolean convertCamel, String... tableNames);

    /**
     * 获取所有的table信息
     * @param convertCamel 是否将数据库的字段  表名 转换为驼峰式
     * @return 返回list
     */
    List<TableInfo> getAll(boolean convertCamel);
}
