package pers.tom.generator.table.factory;

import pers.tom.generator.table.TableInfo;

import java.util.List;

/**
 * @author tom
 * @description tableInfosFactory
 * @date 2021/1/4 22:32
 */
public interface TableInfosFactory {

    /**
     * 获取tableInfos
     * @return 返回结果
     */
    List<TableInfo> getTableInfos();
}
