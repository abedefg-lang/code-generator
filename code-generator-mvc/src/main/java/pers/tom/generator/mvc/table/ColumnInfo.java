package pers.tom.generator.mvc.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import pers.tom.generator.basic.utils.NameUtils;

/**
 * @author tom
 * @description 字段信息
 * @date 2021/1/4 22:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ColumnInfo {

    /**字段名*/
    private String columnName;

    /**字段类型*/
    private String columnType;

    /**字段备注*/
    private String comment;

    /**字段的键*/
    private String columnKey;

    /**该字段是否是主键*/
    private boolean primary;

    /**字段类型转化成java类型*/
    private Class<?> javaType;

    /**转换之后的属性名*/
    private String fieldName;

    /**大驼峰式属性命名*/
    private String greatHumpFieldName;

    public ColumnInfo(String columnName, String columnType, String comment, String columnKey, boolean primary, Class<?> javaType, String fieldName) {
        this.columnName = columnName;
        this.columnType = columnType;
        this.comment = comment;
        this.columnKey = columnKey;
        this.primary = primary;
        this.javaType = javaType;
        this.fieldName = fieldName;
        this.greatHumpFieldName = NameUtils.initialUppercase(fieldName);
    }
}
