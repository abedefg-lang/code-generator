package com.tablesource;

import com.generator.nameconverter.NameConverter;
import com.tablesource.dao.ColumnInfoDao;
import com.tablesource.dao.TableInfoDao;
import com.tablesource.dao.impl.ColumnInfoDaoImpl;
import com.tablesource.dao.impl.TableInfoDaoImpl;
import com.tablesource.info.ColumnInfo;
import com.tablesource.info.TableInfo;
import com.utils.StringArrayUtils;
import lombok.Data;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Data
public class TableSourceImpl implements TableSource{

    /**TableInfoDao  获取table的相关信息*/
    private TableInfoDao tableInfoDao;

    /**ColumnInfoDao 获取某个表的字段的相关信息*/
    private ColumnInfoDao columnInfoDao;

    /**表名称的正则匹配数组*/
    private String[] namePatterns;

    /**是否生成全表*/
    private boolean allTables;

    public TableSourceImpl(){}

    public TableSourceImpl(DataSource dataSource){
        this.tableInfoDao = new TableInfoDaoImpl(dataSource);
        this.columnInfoDao = new ColumnInfoDaoImpl(dataSource);
    }


    @Override
    public List<TableInfo> getTableInfos(NameConverter converter) {
        //获取匹配成功的表的信息
        List<TableInfo> tableInfos = tableInfoDao.selectListByName(getMatchingTableNames());
        //完善信息
        improveInfo(tableInfos, converter);
        return tableInfos;
    }

    /**
     * 获取匹配的表名
     * 全表生成优先于 名字匹配
     * @return 返回表名
     */
    private String[] getMatchingTableNames(){
        //获取所有表名
        List<String> tableNames = tableInfoDao.selectAllTables();
        //如果是全表 直接返回
        if(allTables) return tableNames.toArray(new String[0]);

        if(!StringArrayUtils.isEmpty(namePatterns)){
            List<String> matchNames = new ArrayList<>();
            //获取所有的表名进行匹配
            for(String table : tableNames){
                if(StringArrayUtils.isMatches(namePatterns, table)){
                    matchNames.add(table);
                }
            }
            //返回数组
            return matchNames.toArray(new String[0]);
        }
        return new String[0];
    }



    /**
     * 完善tableInfo的信息  设置这张表对应的字段信息进行
     * @param tableInfos  需要完善的tableInfos
     * @param converter  是否转化成驼峰式
     */
    private void improveInfo(List<TableInfo> tableInfos, NameConverter converter){
        //循环获取column
        List<ColumnInfo> columnBeanList;
        for(TableInfo tableInfo : tableInfos){
            String tableName = tableInfo.getTableName();
            columnBeanList = columnInfoDao.selectListByTableName(tableInfo.getTableName());
            if(converter != null){
                //说明需要转化
                //将字段全部转换成驼峰式
                columnBeanList.forEach(columnBean -> columnBean.setFieldName(converter.toPropertyName(columnBean.getColumnName())));
                //将类名转换成大驼峰式
                tableInfo.setClassName(converter.toClassName(tableName));
            }
            //最后添加字段
            tableInfo.setColumnList(columnBeanList);
        }
    }

    public void setTableNamePatterns(String... namePatterns){
        this.namePatterns = namePatterns;
    }
}
