package com.tablesource.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 存储一张表的信息
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

    /***
     * 在设置字段的时候就可以将主键选出来
     */
    public TableInfo setColumnList(List<ColumnInfo> columnList){
        if(!columnList.isEmpty()){
            //最开始默认获取第一个字段  如果全部字段都没有主键  默认设置第一个第一个字段为主键
            ColumnInfo primary = columnList.get(0);
            for(ColumnInfo columnBean : columnList){
                if("PRI".equals(columnBean.getColumnKey())){
                    primary = columnBean;
                    break;
                }
            }
            this.primary = primary;
        }
        this.columnList = columnList;
        return this;
    }

//    /**
//     * 不同的数据库查询表内容的字段
//     */
//    public static class TableInfoPropertyMapping{
//
//        private String tableNameColumnMapping;
//
//        private String commentColumnMapping;
//    }
}
