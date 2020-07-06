package com.tablesource.dao.impl;

import com.tablesource.dao.ColumnInfoDao;
import com.tablesource.info.ColumnInfo;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ColumnInfoDaoImpl implements ColumnInfoDao {

    private DataSource dataSource;

    public ColumnInfoDaoImpl(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public ColumnInfoDaoImpl(){}

    @Override
    public List<ColumnInfo> selectListByTableName(String tableName) {
        List<ColumnInfo> result = null;

        try(Connection connection = dataSource.getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM `INFORMATION_SCHEMA`.`COLUMNS` WHERE `TABLE_SCHEMA` = \""+connection.getCatalog()+"\" AND `TABLE_NAME` = \""+tableName+"\"");
            ResultSet set = statement.executeQuery()){
            //创建集合
            ColumnInfo info;
            result = new ArrayList<>();
            while(set.next()){
                //链式调用set方法
                info = new ColumnInfo()
                        .setDataType(set.getString("DATA_TYPE"))
                        .setColumnKey(set.getString("COLUMN_KEY"))
                        .setComment(set.getString("COLUMN_COMMENT"))
                        .setColumnName(set.getString("COLUMN_NAME"))
                        .setFieldName(set.getString("COLUMN_NAME"));
                //添加进集合
                result.add(info);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return dataSource;
    }
}
