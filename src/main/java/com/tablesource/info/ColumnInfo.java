package com.tablesource.info;

import com.utils.ColumnTypeUtil;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *  存储表中字段的信息
 */
@Data
@Accessors(chain = true)
public class ColumnInfo {

    /**字段名*/
    private String columnName;

    /**字段类型*/
    private String dataType;

    /**字段备注*/
    private String comment;

    /**字段的键*/
    private String columnKey;

    /**字段类型转化成java类型*/
    private Class<?> javaType;

    /**转换之后的属性名*/
    private String fieldName;

    /**
     * 在设置字段类型的时候  可以同时设置java的类型
     */
    public ColumnInfo setDataType(String dataType){
        this.dataType = dataType;
        this.javaType = ColumnTypeUtil.getJavaType(dataType);
        return this;
    }
}
