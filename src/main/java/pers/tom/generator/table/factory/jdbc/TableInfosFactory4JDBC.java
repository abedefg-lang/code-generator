package pers.tom.generator.table.factory.jdbc;

import pers.tom.generator.table.factory.TableInfosFactory;
import pers.tom.generator.table.factory.jdbc.dialect.Dialect;
import pers.tom.generator.table.typehandler.DbTypeHandler;
import pers.tom.generator.utils.NameUtils;
import pers.tom.generator.table.ColumnInfo;
import pers.tom.generator.table.TableInfo;
import pers.tom.generator.utils.StringArrayUtils;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author tom
 * @description 通过读取数据库的方式创建tableInfos
 * @date 2021/1/6 23:02
 */
@Data
public class TableInfosFactory4JDBC implements TableInfosFactory {

    /**数据库类型处理器*/
    private DbTypeHandler typeHandler;

    /**数据源*/
    private DataSource dataSource;

    /**数据库方言*/
    private Dialect dialect;

    /**字段是否转换成驼峰式命名*/
    private boolean convertToHump;

    /**生成表名称的正则*/
    private String[] namePatterns;

    /**忽略表名称的正则 (忽略是指将已经选择的表去除掉，忽略的前提是该表名匹配中了namePatterns)*/
    private String[] ignoreNamePatterns;

    @Override
    public List<TableInfo> getTableInfos() {

        //检查属性
        Objects.requireNonNull(dataSource, "dataSource不能为null");
        Objects.requireNonNull(dialect, "dialect不能为null");
        typeHandler = typeHandler == null ? new DbTypeHandler() : typeHandler;

        //1，查询出所有的表
        //2，筛选出合适的需要生成的表
        //3，进行设置字段信息
        List<TableInfo> tables = null;
        try{
            tables = this.screen(this.getAllTableInfos());
            this.setColumnInfos(tables);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tables == null ? Collections.emptyList() : tables;
    }


    public void setNamePatterns(String... namePatterns){
        this.namePatterns = namePatterns;
    }

    public void setIgnoreNamePatterns(String... ignoreNamePatterns){
        this.ignoreNamePatterns = ignoreNamePatterns;
    }

    /**
     * 获取出所有的表信息(不包含字段信息)
     * @return 返回表信息
     */
    private List<TableInfo> getAllTableInfos() throws SQLException {
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(dialect.showTablesSql());
            ResultSet resultSet = statement.executeQuery()){
            if(resultSet != null){
                List<TableInfo> tableInfos = new ArrayList<>();
                TableInfo table;
                while(resultSet.next()){
                    String tableName = resultSet.getString(dialect.getTableNameField());

                    //创建并设置表名 类名
                    table = new TableInfo()
                            .setTableName(tableName)
                            .setClassName(NameUtils.convertGreatHump(tableName, "_"))
                            .setComment(resultSet.getString(dialect.getTableCommentField()));
                    tableInfos.add(table);
                }
                return tableInfos;
            }
        }
        return Collections.emptyList();
    }

    /**
     * 筛选tableInfos
     * 规则是这个表能匹配namePatterns中 并且 不能匹配ignoreNamePatterns
     * @param tableInfos tableInfos
     * @return 返回
     */
    private List<TableInfo> screen(List<TableInfo> tableInfos){
        if (!CollectionUtils.isEmpty(tableInfos)) {
            List<TableInfo> result = new ArrayList<>(tableInfos.size());
            String tableName;
            for(TableInfo table : tableInfos){

                //如果当前表符合规则添加
                tableName = table.getTableName();
                if(StringArrayUtils.isMatches(namePatterns, tableName)
                        && !StringArrayUtils.isMatches(ignoreNamePatterns, tableName)){
                    result.add(table);
                }
            }
            return result;
        }
        return Collections.emptyList();
    }

    /**
     * 对tableInfos进行处理  设置属性字段
     * @param tableInfos 带处理的tableInfos
     */
    private void setColumnInfos(List<TableInfo> tableInfos) throws SQLException {
        if(!CollectionUtils.isEmpty(tableInfos)){
            ResultSet resultSet = null;
            try(Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()){
                List<ColumnInfo> columnInfoList;
                for(TableInfo table : tableInfos){
                    resultSet = statement.executeQuery(dialect.showColumnsSql(table.getTableName()));

                    //记录主键
                    ColumnInfo primary = null;
                    ColumnInfo column;
                    columnInfoList = new ArrayList<>();
                    while(resultSet.next()){

                        //获取字段信息
                        String columnName = resultSet.getString(dialect.getColumnNameField());
                        String fieldName = convertToHump ? NameUtils.convertHump(columnName, "_") : columnName;
                        String key = resultSet.getString(dialect.getColumnKeyField());
                        String columnType = resultSet.getString(dialect.getColumnTypeField());
                        String comment = resultSet.getString(dialect.getColumnCommentField());

                        //创建
                        column = new ColumnInfo(columnName, columnType, comment, key, typeHandler.getJavaType(columnType), fieldName);

                        //判断当前字段是否是主键字段
                        if(primary == null){
                            primary = dialect.isPrimaryKey(key) ? column : null;
                        }
                        columnInfoList.add(column);
                    }
                    table.setColumnList(columnInfoList);

                    //如果primary还是为null 获取第一个字段为主键
                    primary = primary == null ? columnInfoList.get(0) : primary;
                    table.setPrimary(primary);
                }
            }finally {
                if(resultSet != null){
                    resultSet.close();
                }
            }   
        }
    }
}
