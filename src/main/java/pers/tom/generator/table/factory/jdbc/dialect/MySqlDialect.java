package pers.tom.generator.table.factory.jdbc.dialect;

/**
 * @author tom
 * @description mysql数据库的语句
 * @date 2021/1/6 23:14
 */
public class MySqlDialect implements Dialect{

    @Override
    public String showTablesSql() {
        return "SHOW TABLE STATUS";
    }

    @Override
    public String showColumnsSql(String tableName) {
        return "SHOW FULL FIELDS FROM " + tableName;
    }

    @Override
    public String getTableNameField() {
        return "NAME";
    }

    @Override
    public String getTableCommentField() {
        return "COMMENT";
    }

    @Override
    public String getColumnNameField() {
        return "FIELD";
    }

    @Override
    public String getColumnTypeField() {
        return "TYPE";
    }

    @Override
    public String getColumnCommentField() {
        return "COMMENT";
    }

    @Override
    public String getColumnKeyField() {
        return "KEY";
    }

    @Override
    public boolean isPrimaryKey(String key) {
        return "PRI".equals(key);
    }
}
