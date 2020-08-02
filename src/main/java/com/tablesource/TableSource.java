package com.tablesource;

import com.utils.converter.NameConverter;
import com.tablesource.info.TableInfo;

import java.util.List;

/**
 * 获取完整的table信息(设置column)
 */
public interface TableSource {

    /**
     * 获取table的信息
     * @param converter 名称转换器
     * @return 返回list
     */
    List<TableInfo> getTableInfos(NameConverter converter);
}
