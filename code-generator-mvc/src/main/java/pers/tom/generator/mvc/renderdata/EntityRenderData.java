package pers.tom.generator.mvc.renderdata;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

import org.springframework.util.CollectionUtils;
import pers.tom.generator.basic.renderdata.JavaFileRenderData;
import pers.tom.generator.mvc.table.ColumnInfo;
import pers.tom.generator.mvc.table.TableInfo;

import java.util.List;

/**
 * @author tom
 * @description entity的渲染数据
 *              entity与数据库中某张表进行对应
 * @date 2021/3/13 11:17
 */
@Getter
@Setter
@ToString
public class EntityRenderData extends JavaFileRenderData {

    /**对应的表信息*/
    private TableInfo table;

    /**是否使用lombok*/
    private boolean enableLombok;

    public EntityRenderData(String packageName, String className, TableInfo table) {
        super(packageName, className);
        this.enableLombok = true;
        this.setTable(table);
    }

    public void setTable(@NonNull TableInfo table) {

        this.table = table;

        List<ColumnInfo> columns = table.getColumns();
        if(!CollectionUtils.isEmpty(columns)){
            columns.forEach(columnInfo -> addImportClass(columnInfo.getJavaType()));
        }
    }
}
