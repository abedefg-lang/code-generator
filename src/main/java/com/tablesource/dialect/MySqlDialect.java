package com.tablesource.dialect;

import com.tablesource.dialect.info.ColumnInfo;
import com.tablesource.dialect.info.TableInfo;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySqlDialect extends AbstractDialect{

    public MySqlDialect(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public List<TableInfo> selectTableInfos() {
        Map<String, String> map = new HashMap<>(4);
        map.put("TABLE_NAME", "tableName");
        map.put("TABLE_COMMENT", "comment");
        return selectList("SELECT * FROM `INFORMATION_SCHEMA`.`TABLES` WHERE `TABLE_SCHEMA`=\""+databaseName+"\"", TableInfo.class, map);
    }

    @Override
    public List<ColumnInfo> selectColumnInfos(String tableName) {
        Map<String, String> map = new HashMap<>(8);
        map.put("COLUMN_NAME", "columnName");
        map.put("DATA_TYPE", "dataType");
        map.put("COLUMN_KEY", "columnKey");
        map.put("COLUMN_COMMENT", "comment");
        return selectList("SELECT * FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_SCHEMA` = \""+databaseName+"\" AND `TABLE_NAME` = \""+tableName+"\" ORDER BY ORDINAL_POSITION", ColumnInfo.class, map);
    }
}
