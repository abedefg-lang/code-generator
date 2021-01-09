package pers.tom.generator.table;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

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

    /**字段类型转化成java类型*/
    private Class<?> javaType;

    /**转换之后的属性名*/
    private String fieldName;

}
