package pers.tom.generator.table.factory.jdbc.dialect;

/**
 * @author tom
 * @description 数据库方言  不同数据库语句 字段很可能不同
 * @date 2021/1/6 23:06
 */
public interface Dialect {

    /**
     * 获取查询表信息的sql
     * @return 返回sql
     */
    String showTablesSql();

    /**
     * 获取查询指定表下面的字段信息的sql
     * @param tableName 指定表名
     * @return 返回sql
     */
    String showColumnsSql(String tableName);

    /**
     * 获取查询表名称的字段
     * @return 返回字段
     */
    String getTableNameField();

    /**
     * 获取查询表注释的字段
     * @return 返回字段
     */
    String getTableCommentField();

    /**
     * 获取查询字段名称的字段
     * @return 返回字段
     */
    String getColumnNameField();

    /**
     * 获取查询字段类型名称的字段
     * @return 返回字段
     */
    String getColumnTypeField();

    /**
     * 获取查询字段注释的字段
     * @return 返回字段
     */
    String getColumnCommentField();

    /**
     * 获取查询字段key的字段
     * @return 返回字段
     */
    String getColumnKeyField();

    /**
     * 判断某个key是否是主键
     * @param key 主键
     * @return 返回boolean
     */
    boolean isPrimaryKey(String key);
}
