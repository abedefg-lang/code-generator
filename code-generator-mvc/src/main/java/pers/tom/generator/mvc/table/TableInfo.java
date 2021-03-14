package pers.tom.generator.mvc.table;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import pers.tom.generator.basic.utils.NameUtils;

import java.util.List;

/**
 * @author tom
 * @description 映射表信息
 * @date 2021/3/14 22:03
 */
@Data
public class TableInfo {

    /**对应表名*/
    private String tableName;

    /**备注*/
    private String comment;

    /**对应的类名*/
    private String className;

    /**该表的所有字段信息*/
    private List<ColumnInfo> columns;

    /**主键字段*/
    private ColumnInfo primary;

    public TableInfo(String tableName, String comment){
        this.tableName = tableName;
        this.comment = comment;
        this.className = NameUtils.convertGreatHump(tableName, "_");
    }

    public void setColumns(List<ColumnInfo> columns){

        this.columns = columns;
        if(!CollectionUtils.isEmpty(columns)){
            for(ColumnInfo column : columns){
                if(primary == null && column.isPrimary()){
                    primary = column;
                }
            }
        }
    }
}
