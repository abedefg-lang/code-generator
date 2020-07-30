package com.tablesource;

import com.tablesource.converter.NameConverter;
import com.tablesource.info.TableInfo;

import java.util.List;

public interface TableSource {

    /**
     * 获取指定的table的信息
     * @param tableNames 指定的表名
     * @param converter 转换器
     * @return 返回list
     */
    List<TableInfo> getTableInfos(NameConverter converter, String... tableNames);

    /**
     * 获取所有的table信息
     * @param converter 转换器
     * @return 返回list
     */
    List<TableInfo> getAll(NameConverter converter);
}
