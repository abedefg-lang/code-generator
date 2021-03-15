package pers.tom.generator.mvc.table.jdbc;

import lombok.Data;
import lombok.NonNull;
import pers.tom.generator.basic.utils.NameUtils;

import pers.tom.generator.mvc.table.ColumnInfo;
import pers.tom.generator.mvc.table.TableInfo;
import pers.tom.generator.mvc.table.TableInfoFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author tom
 * @description 通过查询数据的方式创建table info
 * @date 2021/3/14 22:27
 */
@Data
public class TableInfoFactory4Jdbc implements TableInfoFactory {

    /**数据源*/
    private DataSource dataSource;

    /**类型处理器*/
    private DbTypeHandler typeHandler;

    /**数据库方言*/
    private Dialect dialect;

    /**字段是否转换成驼峰式风格*/
    private boolean humpStyle;

    /**是否生成所有表*/
    private boolean allTable;

    /**需要生成的表名的正则*/
    private String[] namePattens;


    public TableInfoFactory4Jdbc(@NonNull DataSource dataSource){

        this(dataSource, new MySqlDialect());
    }

    public TableInfoFactory4Jdbc(@NonNull DataSource dataSource,
                                 @NonNull Dialect dialect){

        this.dataSource = dataSource;
        this.dialect = dialect;
        this.typeHandler = new DbTypeHandler();
    }

    @Override
    public Collection<TableInfo> get() {

        Collection<TableInfo> collection = null;
        try {
            collection = this.getValidTableInfoCollection();
            for(TableInfo tableInfo : collection){
                this.setColumns(tableInfo);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return collection == null ? Collections.emptyList() : collection;
    }

    /**
     * 获取有效的table info
     * 1，查询所有的表名
     * 2，记录成功匹配正则的table
     * @return list
     */
    private Collection<TableInfo> getValidTableInfoCollection() throws SQLException {

        String sql = dialect.showTablesSql();
        List<TableInfo> list = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while(resultSet.next()){
                //获取表信息
                String tableName = resultSet.getString(dialect.getTableName());
                String comment = resultSet.getString(dialect.getTableComment());
                if(this.isMatches(tableName)){

                    list.add(new TableInfo(tableName, comment));
                }
            }
        }
        return list;
    }

    /**
     * 设置entity数据中的字段
     * @param tableInfo table info
     */
    private void setColumns(TableInfo tableInfo) throws SQLException {

        //从方言获取查询字段的sql
        String sql = dialect.showColumnsSql(tableInfo.getTableName());
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            //将结果封装成字段信息
            List<ColumnInfo> columns = new ArrayList<>();
            while (resultSet.next()){
                //获取字段信息
                String columnName = resultSet.getString(dialect.getColumnName());
                String fieldName = humpStyle ? NameUtils.convertHump(columnName, "_") : columnName;
                String key = resultSet.getString(dialect.getColumnKey());
                boolean isPrimary = dialect.isPrimaryKey(key);
                String columnType = resultSet.getString(dialect.getColumnType());
                String comment = resultSet.getString(dialect.getColumnComment());
                Class<?> javaType = typeHandler.handle(columnType);

                columns.add(new ColumnInfo(columnName, columnType, comment, key, isPrimary, javaType, fieldName));
            }

            //设置字段
            tableInfo.setColumns(columns);
        }
    }

    /**
     * 判断指定的表名是否可以生成
     * @param tableName tableName
     * @return boolean
     */
    private boolean isMatches(String tableName){
        //如果是允许生成全表直接返回true
        if(allTable){
            return true;
        }
        //不是全表 需要匹配正则
        if(namePattens != null && namePattens.length != 0){
            for(String patten : namePattens){
                if(tableName.matches(patten)){
                    return true;
                }
            }
        }
        return false;
    }

}
