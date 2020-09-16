package com.tablesource.dialect;

import com.tablesource.dialect.info.ColumnInfo;
import com.tablesource.dialect.info.TableInfo;

import java.util.List;

/**
 * 对于不同的数据库获取数据的方式不同
 * 需要对不同的数据库提供自己获取数据的方式
 */
public interface Dialect {

    /**
     * 查询全部表信息
     * @return 返回表信息list
     */
    List<TableInfo> selectTableInfos();

    /**
     * 查询指定表名的字段信息
     * @param tableName 指定表明
     * @return 返回字段信息
     */
    List<ColumnInfo> selectColumnInfos(String tableName);
}
