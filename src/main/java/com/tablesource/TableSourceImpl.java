package com.tablesource;

import com.tablesource.dao.ColumnInfoDao;
import com.tablesource.dao.TableInfoDao;
import com.tablesource.dao.impl.ColumnInfoDaoImpl;
import com.tablesource.dao.impl.TableInfoDaoImpl;
import com.tablesource.info.ColumnInfo;
import com.tablesource.info.TableInfo;
import com.utils.NameUtils;
import lombok.Data;

import javax.sql.DataSource;
import java.util.List;


@Data
public class TableSourceImpl implements TableSource{

    private TableInfoDao tableInfoDao;

    private ColumnInfoDao columnInfoDao;

    public TableSourceImpl(TableInfoDao tableInfoDao, ColumnInfoDao columnInfoDao){
        this.tableInfoDao = tableInfoDao;
        this.columnInfoDao = columnInfoDao;
    }

    public TableSourceImpl(DataSource dataSource){
        this.tableInfoDao = new TableInfoDaoImpl(dataSource);
        this.columnInfoDao = new ColumnInfoDaoImpl(dataSource);
    }

    @Override
    public List<TableInfo> getTableInfos(boolean convertCamel, String... tableNames) {
        //获取指定表名的info
        List<TableInfo> tableInfos = tableInfoDao.selectListByName(tableNames);
        this.improveInfo(tableInfos, convertCamel);
        return tableInfos;
    }

    @Override
    public List<TableInfo> getAll(boolean convertCamel) {
        //获取所有的tableInfo
        List<TableInfo> tableInfos = tableInfoDao.selectAll();
        this.improveInfo(tableInfos, convertCamel);
        return tableInfos;
    }


    /**
     * 完善tableInfo的信息  设置这张表对应的字段信息进行
     * @param tableInfos  需要完善的tableInfos
     * @param convertCamel  是否转化成驼峰式
     */
    private void improveInfo(List<TableInfo> tableInfos, boolean convertCamel){
        //循环获取column
        List<ColumnInfo> columnBeanList;
        String className;
        for(TableInfo tableInfo : tableInfos){
            className = tableInfo.getTableName();
            columnBeanList = columnInfoDao.selectListByTableName(tableInfo.getTableName());
            if(convertCamel){
                //说明需要转化
                //将字段全部转换成驼峰式
                columnBeanList.forEach(columnBean -> columnBean.setFieldName(NameUtils.convertCamel(columnBean.getColumnName(), "_")));
                //将类名转换成大驼峰式
                className = NameUtils.convertClassName(tableInfo.getTableName(), "_");
            }
            //设置className
            tableInfo.setClassName(className);
            //最后添加字段
            tableInfo.setColumnList(columnBeanList);
        }
    }
}
