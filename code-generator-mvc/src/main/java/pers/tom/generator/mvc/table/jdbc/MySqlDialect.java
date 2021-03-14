package pers.tom.generator.mvc.table.jdbc;


/**
 * @author tom
 * @description mysql数据库的语句
 * @date 2021/1/6 23:14
 */
public class MySqlDialect implements Dialect {

    @Override
    public String showTablesSql() {
        return "SHOW TABLE STATUS";
    }

    @Override
    public String showColumnsSql(String tableName) {
        return "SHOW FULL FIELDS FROM " + tableName;
    }

    @Override
    public String getTableName() {
        return "NAME";
    }

    @Override
    public String getTableComment() {
        return "COMMENT";
    }

    @Override
    public String getColumnName() {
        return "FIELD";
    }

    @Override
    public String getColumnType() {
        return "TYPE";
    }

    @Override
    public String getColumnComment() {
        return "COMMENT";
    }

    @Override
    public String getColumnKey() {
        return "KEY";
    }

    @Override
    public boolean isPrimaryKey(String key) {
        return "PRI".equals(key);
    }
}
