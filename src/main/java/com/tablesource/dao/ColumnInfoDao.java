package com.tablesource.dao;

import com.tablesource.entity.ColumnInfo;

import java.util.List;

public interface ColumnInfoDao {

    /**
     * 获取指定的表的字段信息
     * @param tableName 指定表名
     * @return 返回list
     */
    List<ColumnInfo> selectListByTableName(String tableName);
}
