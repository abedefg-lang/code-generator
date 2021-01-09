package pers.tom.generator.table;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author tom
 * @description 表信息
 * @date 2021/1/4 22:38
 */
@Data
@Accessors(chain = true)
public class TableInfo {

    /**表名*/
    private String tableName;

    /**备注*/
    private String comment;

    /**转换之后的类名字*/
    private String className;

    /**一张表的所有字段信息*/
    private List<ColumnInfo> columnList;

    /**主键字段*/
    private ColumnInfo primary;


}
