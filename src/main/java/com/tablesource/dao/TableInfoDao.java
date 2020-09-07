package com.tablesource.dao;

import com.tablesource.entity.TableInfo;

import java.util.List;

public interface TableInfoDao {

    /**
     * 获取指定的表名的表信息
     * @param tableNames  指定表名
     * @return 返回list
     */
    List<TableInfo> selectListByName(String... tableNames);

    /**
     * 获取所有表名
     * @return 返回list
     */
    List<String> selectAllTables();

}
