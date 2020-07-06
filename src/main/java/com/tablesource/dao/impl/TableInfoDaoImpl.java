package com.tablesource.dao.impl;

import com.tablesource.dao.TableInfoDao;
import com.tablesource.info.TableInfo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TableInfoDaoImpl implements TableInfoDao {

    private DataSource dataSource;

    public TableInfoDaoImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public TableInfoDaoImpl(){}

    @Override
    public List<TableInfo> selectListByName(String... tableNames) {
        //如果调用的是这个方法  使用in关键字
        //需要先拼接in的内容
        StringBuilder in = new StringBuilder();
        for(String tableName : tableNames){
            in.append("'").append(tableName).append("',");
        }
        return selectList("SELECT * FROM `INFORMATION_SCHEMA`.`TABLES` WHERE `TABLE_SCHEMA`=\""+getDatabasesName()+"\" AND `TABLE_NAME` in ("+in.substring(0, in.length()-1)+")");
    }

    @Override
    public List<TableInfo> selectAll() {
        return selectList("SELECT * FROM `INFORMATION_SCHEMA`.`TABLES` WHERE `TABLE_SCHEMA`=\""+getDatabasesName()+"\"");
    }

    /**
     * 查询的条件不同但是  查询完成之后的结果是相同的
     * 可以将sql语句提出来  将相同的部分封装成起来
     * @param sql 传递执行的sql
     * @return 返回list
     */
    private List<TableInfo> selectList(String sql){
        List<TableInfo> result = null;
        //查询出所有的表
        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet set = statement.executeQuery()){
            //创建集合
            result = new ArrayList<>();
            TableInfo info;
            while(set.next()){
                info = new TableInfo()
                        .setTableName(set.getString("TABLE_NAME"))
                        .setComment(set.getString("TABLE_COMMENT"))
                        .setClassName(set.getString("TABLE_NAME"));
                result.add(info);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 获取使用的数据库名
     * @return 返回数据库名
     */
    private String getDatabasesName(){
        String databaseName = null;
        try(Connection connection = dataSource.getConnection()){
            databaseName = connection.getCatalog();
        }catch (Exception e){
            e.printStackTrace();
        }
        return databaseName;
    }
}
